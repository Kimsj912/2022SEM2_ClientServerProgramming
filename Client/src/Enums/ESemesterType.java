package Enums;

import java.util.ArrayList;

public enum ESemesterType {
    SPRING("01"),
    SUMMER("SM"),
    FALL("02"),
    WINTER("WT");

    private String semester;

    ESemesterType(String semester){
        this.semester = semester;
    }

    public String getSemester(){
        return semester;
    }

    public static ArrayList<String> getSemesterList(){
        ArrayList<String> semesterList = new ArrayList<>();
        for(ESemesterType semesterType : ESemesterType.values()){
            semesterList.add(semesterType.getSemester());
        }
        return semesterList;
    }
}
