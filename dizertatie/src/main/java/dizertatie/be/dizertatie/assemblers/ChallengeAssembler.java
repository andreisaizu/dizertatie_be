package dizertatie.be.dizertatie.assemblers;

import dizertatie.be.dizertatie.controller.responses.*;
import dizertatie.be.dizertatie.domain.bean.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChallengeAssembler {
    public static ChallengeDto assembleChallengeDto(Challenge challenge) {
        return ChallengeDto.builder()
                .id(challenge.getId())
                .title(challenge.getTitle())
                .challengeItemList(assembleChallengeItemList(challenge.getChallengeItemList()))
                .build();
    }

    public static ChallengeItemDto assembleChallengeItemDto(ChallengeItem challengeItem) {
        return ChallengeItemDto.builder()
                .id(challengeItem.getId().intValue())
                .description(challengeItem.getDescription())
                .challengeItemType(challengeItem.getChallengeItemType())
                .challengeItemTaskList(assembleChallengeItemTaskList(challengeItem.getChallengeItemTaskList()))
                .build();
    }

    public static ChallengeItemTaskDto assembleChallengeItemTaskDto(ChallengeItemTask challengeItemTask) {
        return ChallengeItemTaskDto.builder()
                .id(challengeItemTask.getId())
                .question(challengeItemTask.getQuestion())
                .choiceList(assembleItemTaskChoiceDtoList(challengeItemTask.getChoiceItemList()))
                .build();
    }

    public static List<ChoiceDto> assembleItemTaskChoiceDtoList(List<ChoiceItem> choiceItemList) {
        List<ChoiceDto> choiceDtoList = new ArrayList<>();
        for (ChoiceItem choiceItem : choiceItemList) {
            ChoiceDto choiceDto = assembleItemTaskChoiceDto(choiceItem);
            choiceDtoList.add(choiceDto);
        }
        return choiceDtoList;
    }

    public static ChoiceDto assembleItemTaskChoiceDto(ChoiceItem choiceItem) {
        return ChoiceDto.builder()
                .id(choiceItem.getId())
                .correct(choiceItem.getCorrect())
                .choiceValueDtoList(assembleChoiceValueDtoList(choiceItem.getChoiceItemValueList()))
                .build();
    }

    private static List<ChoiceValueDto> assembleChoiceValueDtoList(List<ChoiceItemValue> choiceItemValueList) {
        List<ChoiceValueDto> choiceValueDtoList = new ArrayList<>();
        for (ChoiceItemValue choiceItemValue : choiceItemValueList) {
            ChoiceValueDto choiceValueDto = assembleChoiceValueDto(choiceItemValue);
            choiceValueDtoList.add(choiceValueDto);
        }
        return choiceValueDtoList;
    }


    private static ChoiceValueDto assembleChoiceValueDto(ChoiceItemValue choiceItemValue) {
        return ChoiceValueDto.builder()
                .id(choiceItemValue.getId())
                .value(choiceItemValue.getValue())
                .build();
    }


    public static List<ChallengeItemDto> assembleChallengeItemList(List<ChallengeItem> challengeItemList) {
        List<ChallengeItemDto> challengeItemDtoList = new ArrayList<>();
        for (ChallengeItem challengeItem : challengeItemList) {
            ChallengeItemDto challengeItemDto = assembleChallengeItemDto(challengeItem);
            challengeItemDtoList.add(challengeItemDto);
        }
        Collections.sort(challengeItemDtoList);
        return challengeItemDtoList;
    }

    public static List<ChallengeItemTaskDto> assembleChallengeItemTaskList(List<ChallengeItemTask> challengeItemTaskList) {
        List<ChallengeItemTaskDto> challengeItemTaskDtoList = new ArrayList<>();
        for (ChallengeItemTask challengeItemTask : challengeItemTaskList) {
            ChallengeItemTaskDto challengeItemTaskDto = assembleChallengeItemTaskDto(challengeItemTask);
            challengeItemTaskDtoList.add(challengeItemTaskDto);
        }
        return challengeItemTaskDtoList;
    }


}
