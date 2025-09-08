package AUT.utilities;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Locale;

public class PrepareTestDataForFTEPlanning {
    public static void main(String[] args) throws ParseException {

        String processStartDate = DataReader.getValue("STARTDATE");
        String costDate = DataReader.getValue("COSTDATE");
        String roleDate = DataReader.getValue("ROLEDATE");
        String resourceDate = DataReader.getValue("RESOURCEDATE");
        String fteStartDate = DataReader.getValue("FTEDATE1");
        String fteEndDate = DataReader.getValue("FTEDATE2");

        System.out.println("=================================");
        System.out.println(" processStartDate >> " + processStartDate);
        System.out.println(" costDate >> " + costDate);
        System.out.println(" roleDate >> " + roleDate);
        System.out.println(" resourceDate >> " + resourceDate);
        System.out.println("=================================");
        /*System.out.println("ONBOARD >>" + getOnBoardMap(startDate,costDate));
        System.out.println("COST >> " + getcostORRoleORResourceMap(startDate,costDate));
        System.out.println("=================================");
        System.out.println("ONBOARD >>" +getOnBoardMap(startDate,roleDate));
        System.out.println("ROLE >> " +getcostORRoleORResourceMap(startDate,roleDate));
        System.out.println("=================================");
        System.out.println("ONBOARD >>" +getOnBoardMap(startDate,resourceDate));
        System.out.println("RESOURCE >> " + getcostORRoleORResourceMap(startDate,resourceDate));*/

        System.out.println(getExpectedFTEMapAfterFTEDataisUpdated(processStartDate, fteStartDate,fteEndDate));
        System.out.println("=================================");
    }



    public static LinkedHashMap<String, String> getOnBoardMap(String firstDate, String secondDate) {
        LinkedHashMap<String, String> onBoardMap = new LinkedHashMap<>();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            LocalDate startDate = LocalDate.parse(firstDate, formatter);
            //LocalDate temp = startDate;
            LocalDate endDate = LocalDate.parse(secondDate, formatter);
            Period diff = Period.between(startDate, endDate);
            //System.out.println("diff.getMonths() >> " + diff.getMonths());
            int months = diff.getMonths();
            for (int i = 0; i < 24; i++) {
                String strKey = "";
                if(i==0) {
                    strKey = startDate.format(DateTimeFormatter.ofPattern("MMM/yy")).toString() + " (ACTUAL)";
                } else {
                    strKey = startDate.format(DateTimeFormatter.ofPattern("MMM/yy")).toString() + " (PLAN)";
                }
                if(strKey.contains("Sep")) {
                    strKey = strKey.replaceFirst("Sep","Sept");
                }
                if (i < months) {
                    //System.out.println("value should be 1");
                    onBoardMap.put(strKey, "1.000");
                } else {
                    // System.out.println("value should be 0");
                    onBoardMap.put(strKey, "0.000");
                }
                startDate = startDate.plusMonths(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return onBoardMap;
    }


    public static LinkedHashMap<String, String> getcostORRoleORResourceMap(String firstDate, String secondDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        LocalDate startDate = LocalDate.parse(firstDate, formatter);
        LocalDate endDate = LocalDate.parse(secondDate, formatter);

        Period diff = Period.between(startDate,endDate);
        //System.out.println("diff.getMonths() >> " + diff.getMonths());
        int months = diff.getMonths();

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (int i=0;i<24;i++) {
            String strKey = "";
            if(i==0) {
                strKey = startDate.format(DateTimeFormatter.ofPattern("MMM/yy")).toString() + " (ACTUAL)";
            } else {
                strKey = startDate.format(DateTimeFormatter.ofPattern("MMM/yy")).toString() + " (PLAN)";
            }
            if(strKey.contains("Sep")) {
                strKey = strKey.replaceFirst("Sep","Sept");
            }
            if(i>=months) {
                //System.out.println("value should be 1");
                map.put(strKey,"1.000");
            } else {
                // System.out.println("value should be 0");
                map.put(strKey,"0.000");
            }
            startDate = startDate.plusMonths(1);
        }

        return map;
    }

    public static LinkedHashMap<String, String> getCostMapAfterResourceTypeChanges(String firstDate, String secondDate, String thirdDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        LocalDate startDate = LocalDate.parse(firstDate, formatter);
        LocalDate middleDate = LocalDate.parse(secondDate, formatter);
        LocalDate endDate = LocalDate.parse(thirdDate, formatter);

        Period diff = Period.between(startDate,middleDate);

        int months1 = diff.getMonths();
        System.out.println("months1 >> " + months1);
        Period diff1 = Period.between(middleDate,endDate);
        int months2 = diff1.getMonths();
        System.out.println("months2 >> " + months2);
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        for (int i=0;i<24;i++) {
            String strKey = "";
            if(i==0) {
                strKey = startDate.format(DateTimeFormatter.ofPattern("MMM/yy")).toString() + " (ACTUAL)";
            } else {
                strKey = startDate.format(DateTimeFormatter.ofPattern("MMM/yy")).toString() + " (PLAN)";
            }
            if(strKey.contains("Sep")) {
                strKey = strKey.replaceFirst("Sep","Sept");
            }

            if(i>=months1 && i <(months1+months2)) {
                //System.out.println("value should be 1");
                map.put(strKey,"1.000");
            } else {
                // System.out.println("value should be 0");
                map.put(strKey,"0.000");
            }
            startDate = startDate.plusMonths(1);
        }

        return map;
    }


    public static LinkedHashMap<String, String> getExpectedFTEMapAfterFTEDataisUpdated(String processStartDate, String fteStartDate, String fteEndDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        LocalDate startDate = LocalDate.parse(fteStartDate, formatter);
        LocalDate endDate = LocalDate.parse(fteEndDate, formatter);

        Period diff = Period.between(startDate,endDate);

        int months = diff.getMonths();
        //System.out.println("months >> " + months);

        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        for (int i=0;i<months;i++) {
            String strKey = "";
            if(i==0 && processStartDate.equalsIgnoreCase(fteStartDate)) {
                strKey = startDate.format(DateTimeFormatter.ofPattern("MMM/yy")).toString() + " (ACTUAL)";
            } else {
                strKey = startDate.format(DateTimeFormatter.ofPattern("MMM/yy")).toString() + " (PLAN)";
            }
            if(strKey.contains("Sep")) {
                strKey = strKey.replaceFirst("Sep","Sept");
            }
            map.put(strKey,"0.500");
            startDate = startDate.plusMonths(1);
        }

        return map;
    }

}
