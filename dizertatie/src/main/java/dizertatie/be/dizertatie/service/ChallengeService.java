package dizertatie.be.dizertatie.service;

import dizertatie.be.dizertatie.controller.request.*;
import dizertatie.be.dizertatie.controller.responses.ChallengeDto;
import dizertatie.be.dizertatie.domain.bean.*;
import dizertatie.be.dizertatie.domain.service.ChallengeDomainService;
import dizertatie.be.dizertatie.enums.ChallengeTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static dizertatie.be.dizertatie.assemblers.ChallengeAssembler.assembleChallengeDto;
import static dizertatie.be.dizertatie.assemblers.ChallengeAssembler.assembleItemTaskChoiceDtoList;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeDomainService challengeDomainService;

    public ChallengeDto getChallengeDtoByTemplate(ChallengeTemplate challengeTemplate) {
        Challenge challenge = challengeDomainService.findById(1L);
        return assembleChallengeDto(challenge);
    }

    public ValidatedChallengeAnswer validateAnswer(ChallengeAnswer receivedChallengeAnswer) {
        ValidatedChallengeAnswer validatedChallengeAnswer = new ValidatedChallengeAnswer();
        validatedChallengeAnswer.setChallengeId(receivedChallengeAnswer.getChallengeId());

        Challenge challenge = challengeDomainService.findById(receivedChallengeAnswer.getChallengeId());
        List<ChallengeItemAnswer> receivedChallengeItemAnswerList = receivedChallengeAnswer.getChallengeItemAnswers();

        for (ChallengeItemAnswer receivedChallengeItemAnswer : receivedChallengeItemAnswerList) {
            ValidatedChallengeItemAnswer validatedChallengeItemAnswer = new ValidatedChallengeItemAnswer();
            validatedChallengeItemAnswer.setChallengeItemId(receivedChallengeItemAnswer.getChallengeItemId());

            ChallengeItem challengeItem = challenge.getChallengeItemList().stream()
                    .filter(ch -> ch.getId() == receivedChallengeItemAnswer.getChallengeItemId())
                    .findFirst()
                    .get();

            validatedChallengeItemAnswer.setType(challengeItem.getChallengeItemType());

            List<ChallengeItemTaskAnswer> receivedTaskAnswers = receivedChallengeItemAnswer.getTaskAnswers();
            for (ChallengeItemTaskAnswer receivedTaskAnswer : receivedTaskAnswers) {

                ValidatedChallengeItemTaskAnswer validatedChallengeItemTaskAnswer = new ValidatedChallengeItemTaskAnswer();

                validatedChallengeItemTaskAnswer.setChallengeItemTaskId(receivedTaskAnswer.getChallengeItemTaskId());

                ChallengeItemTask challengeItemTask = challengeItem.getChallengeItemTaskList().stream()
                        .filter(tsk -> tsk.getId() == receivedTaskAnswer.getChallengeItemTaskId())
                        .findFirst()
                        .get();

                validatedChallengeItemTaskAnswer.setQuestion(challengeItemTask.getQuestion());

                List<ChoiceItem> choiceList = challengeItemTask.getChoiceItemList();
                List<Long> selectedChoiceIds = receivedTaskAnswer.getSelectedChoicesIds();
                List<TaskChoiceDto> taskChoiceDtoList = new ArrayList<>();
                for (ChoiceItem choiceItem : choiceList) {
                    TaskChoiceDto taskChoiceDto = TaskChoiceDto.builder()
                            .values(choiceItem.getChoiceItemValueList().stream().map(ChoiceItemValue::getValue).collect(Collectors.toList()))
                            .id(choiceItem.getId())
                            .correct(choiceItem.getCorrect())
                            .selected(false)
                            .build();
                    if (selectedChoiceIds.contains(choiceItem.getId())) {
                        taskChoiceDto.setSelected(true);
                    }
                    taskChoiceDtoList.add(taskChoiceDto);
                }

                List<ChoiceItem> correctChoiceList = choiceList.stream()
                        .filter(ChoiceItem::getCorrect)
                        .collect(Collectors.toList());


                validatedChallengeItemTaskAnswer.setExplanation(challengeItemTask.getExplanation());
                validatedChallengeItemTaskAnswer.setPoints(0);


                boolean correctTask = false;
                validatedChallengeItemTaskAnswer.setPoints(0);
                List<TaskChoiceDto> presumedCorrectList = taskChoiceDtoList.stream()
                        .filter(TaskChoiceDto::isSelected)
                        .filter(TaskChoiceDto::isCorrect)
                        .collect(Collectors.toList());
                if(presumedCorrectList.size() == selectedChoiceIds.size()){
                    correctTask = true;
                    validatedChallengeItemTaskAnswer.setPoints(challengeItemTask.getPoints());
                }

                validatedChallengeItemTaskAnswer.setCorrect(correctTask);
                validatedChallengeItemTaskAnswer.setTaskChoiceDtoList(taskChoiceDtoList);
                validatedChallengeItemTaskAnswer.setCorrectChoices(assembleItemTaskChoiceDtoList(correctChoiceList));
                validatedChallengeItemAnswer.add(validatedChallengeItemTaskAnswer);

            }
            validatedChallengeAnswer.add(validatedChallengeItemAnswer);
        }
        return validatedChallengeAnswer;
    }
}
