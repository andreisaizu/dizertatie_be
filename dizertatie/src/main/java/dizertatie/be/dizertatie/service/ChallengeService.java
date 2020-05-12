package dizertatie.be.dizertatie.service;

import dizertatie.be.dizertatie.controller.request.ChallengeAnswer;
import dizertatie.be.dizertatie.controller.request.ChallengeItemAnswer;
import dizertatie.be.dizertatie.controller.request.ChallengeItemTaskAnswer;
import dizertatie.be.dizertatie.controller.request.TaskChoiceDto;
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

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeDomainService challengeDomainService;

    public ChallengeDto getChallengeDtoByTemplate(ChallengeTemplate challengeTemplate) {
        Challenge challenge = challengeDomainService.findById(1L);
        return assembleChallengeDto(challenge);
    }

    public ChallengeAnswer validateAnswer(ChallengeAnswer receivedChallengeAnswer) {
        ChallengeAnswer newchallengeAnswer = new ChallengeAnswer();
        newchallengeAnswer.setChallengeId(receivedChallengeAnswer.getChallengeId());

        Challenge challenge = challengeDomainService.findById(receivedChallengeAnswer.getChallengeId());
        List<ChallengeItemAnswer> receivedChallengeItemAnswerList = receivedChallengeAnswer.getChallengeItemAnswers();
        for (ChallengeItemAnswer receivedChallengeItemAnswer : receivedChallengeItemAnswerList) {
            ChallengeItemAnswer newChallengeItemAnswer = new ChallengeItemAnswer();
            newChallengeItemAnswer.setChallengeItemId(receivedChallengeItemAnswer.getChallengeItemId());

            ChallengeItem challengeItem = challenge.getChallengeItemList().stream()
                    .filter(ch -> ch.getId() == receivedChallengeItemAnswer.getChallengeItemId())
                    .findFirst()
                    .get();

            List<ChallengeItemTaskAnswer> receivedTaskAnswers = receivedChallengeItemAnswer.getTaskAnswers();
            for (ChallengeItemTaskAnswer receivedTaskAnswer : receivedTaskAnswers) {

                ChallengeItemTaskAnswer newChallengeTaskAnswer = new ChallengeItemTaskAnswer();

                newChallengeTaskAnswer.setChallengeItemTaskId(receivedTaskAnswer.getChallengeItemTaskId());
                newChallengeTaskAnswer.setSelectedChoicesIds(receivedTaskAnswer.getSelectedChoicesIds());

                ChallengeItemTask challengeItemTask = challengeItem.getChallengeItemTaskList().stream()
                        .filter(tsk -> tsk.getId() == receivedTaskAnswer.getChallengeItemTaskId())
                        .findFirst()
                        .get();

                newChallengeTaskAnswer.setQuestion(challengeItemTask.getQuestion());

                List<ChoiceItem> choiceList = challengeItemTask.getChoiceItemList();
                List<Long> selectedChoiceIds = receivedTaskAnswer.getSelectedChoicesIds();
                List<TaskChoiceDto> taskChoiceDtoList = new ArrayList<>();
                for (ChoiceItem choiceItem : choiceList) {
                    TaskChoiceDto taskChoiceDto =TaskChoiceDto.builder()
                            .value(choiceItem.getValue())
                            .id(choiceItem.getId())
                            .correct(choiceItem.getCorrect())
                            .selected(false)
                            .build();
                    if(selectedChoiceIds.contains(choiceItem.getId())){
                        taskChoiceDto.setSelected(true);
                    }
                    taskChoiceDtoList.add(taskChoiceDto);
                }


                List<Long> correctChoiceList = choiceList.stream()
                        .filter(ChoiceItem::getCorrect)
                        .map(ChoiceItem::getId)
                        .collect(Collectors.toList());

                List<String> correctChoiceValueList = choiceList.stream()
                        .filter(ChoiceItem::getCorrect)
                        .map(ChoiceItem::getValue)
                        .collect(Collectors.toList());
                boolean correctTask = true;


                for (Long choiceId : selectedChoiceIds) {

                    if (!correctChoiceList.contains(choiceId)) {
                        correctTask = false;
                        newChallengeTaskAnswer.setExplanation(challengeItemTask.getExplanation());
                    }
                }



                newChallengeTaskAnswer.setCorrect(correctTask);
                newChallengeTaskAnswer.setTaskChoiceDtoList(taskChoiceDtoList);
                newChallengeTaskAnswer.setCorrectChoicesValues(correctChoiceValueList);
                newChallengeItemAnswer.add(newChallengeTaskAnswer);

            }
            newchallengeAnswer.add(newChallengeItemAnswer);
        }
        return newchallengeAnswer;
    }
}
