package uz.klimuz.soundengineer;

import android.util.Log;

import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.CellBorderType;
import com.aspose.cells.Color;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

public class SheetCreator {
    public static Workbook createExcelWorkbook() {
        Workbook workbook = new Workbook();
        Worksheet sheet = workbook.getWorksheets().get(0);

        Cell cell;
        Style styleHeader = workbook.createStyle();

        styleHeader.setBorder(BorderType.BOTTOM_BORDER, CellBorderType.MEDIUM, Color.getBlack());
        styleHeader.setBorder(BorderType.LEFT_BORDER, CellBorderType.MEDIUM, Color.getBlack());
        styleHeader.setBorder(BorderType.TOP_BORDER, CellBorderType.MEDIUM, Color.getBlack());
        styleHeader.setBorder(BorderType.RIGHT_BORDER, CellBorderType.MEDIUM, Color.getBlack());

        styleHeader.setHorizontalAlignment(TextAlignmentType.CENTER);
        styleHeader.getFont().setBold(true);

        cell = sheet.getCells().get("A1");
        cell.setValue("Num");
        cell.setStyle(styleHeader);

        cell = sheet.getCells().get("B1");
        cell.setValue("RIO NAME");
        cell.setStyle(styleHeader);

        cell = sheet.getCells().get("C1");
        cell.setValue("RIO No");
        cell.setStyle(styleHeader);

        cell = sheet.getCells().get("D1");
        cell.setValue("NAME");
        cell.setStyle(styleHeader);

        cell = sheet.getCells().get("E1");
        cell.setValue("PICKUP");
        cell.setStyle(styleHeader);

        cell = sheet.getCells().get("F1");
        cell.setValue("NOTE");
        cell.setStyle(styleHeader);

        Style styleLeft = workbook.createStyle();
        styleLeft.setBorder(BorderType.LEFT_BORDER, CellBorderType.MEDIUM, Color.getBlack());
        styleLeft.setHorizontalAlignment(TextAlignmentType.CENTER);

        Style styleRight = workbook.createStyle();
        styleRight.setBorder(BorderType.RIGHT_BORDER, CellBorderType.MEDIUM, Color.getBlack());
        styleRight.setHorizontalAlignment(TextAlignmentType.CENTER);

        Style styleMiddle = workbook.createStyle();
        styleMiddle.setHorizontalAlignment(TextAlignmentType.CENTER);

        Style styleBottom = workbook.createStyle();
        styleBottom.setBorder(BorderType.BOTTOM_BORDER, CellBorderType.MEDIUM, Color.getBlack());
        styleBottom.setHorizontalAlignment(TextAlignmentType.CENTER);

        Style styleBottomLeft = workbook.createStyle();
        styleBottomLeft.setBorder(BorderType.BOTTOM_BORDER, CellBorderType.MEDIUM, Color.getBlack());
        styleBottomLeft.setBorder(BorderType.LEFT_BORDER, CellBorderType.MEDIUM, Color.getBlack());
        styleBottomLeft.setHorizontalAlignment(TextAlignmentType.CENTER);

        Style styleBottomRight = workbook.createStyle();
        styleBottomRight.setBorder(BorderType.BOTTOM_BORDER, CellBorderType.MEDIUM, Color.getBlack());
        styleBottomRight.setBorder(BorderType.RIGHT_BORDER, CellBorderType.MEDIUM, Color.getBlack());
        styleBottomRight.setHorizontalAlignment(TextAlignmentType.CENTER);

        for (Channel channel: ChannelListActivity.channels){
            boolean isBottom = (channel.getNumber() == ChannelListActivity.channels.size());
            //number
            cell = sheet.getCells().get("A" + (channel.getNumber() + 1));
            cell.setValue(String.valueOf(channel.getNumber()));
            if (isBottom){
                cell.setStyle(styleBottomLeft);
            }else {
                cell.setStyle(styleLeft);
            }
            //rio name
            cell = sheet.getCells().get("B" + (channel.getNumber() + 1));
            cell.setValue(channel.getRioName());
            if (isBottom){
                cell.setStyle(styleBottom);
            }else {
                cell.setStyle(styleMiddle);
            }
//            String cellValue = cell.getStringValue();
//            Log.i("myLog", cellValue);
            //rio number
            cell = sheet.getCells().get("C" + (channel.getNumber() + 1));
            cell.setValue(channel.getRioNumber());
            if (isBottom){
                cell.setStyle(styleBottom);
            }else {
                cell.setStyle(styleMiddle);
            }
            //name
            cell = sheet.getCells().get("D" + (channel.getNumber() + 1));
            cell.setValue(channel.getName());
            if (isBottom){
                cell.setStyle(styleBottom);
            }else {
                cell.setStyle(styleMiddle);
            }
            //pickup
            cell = sheet.getCells().get("E" + (channel.getNumber() + 1));
            cell.setValue(channel.getPickup());
            if (isBottom){
                cell.setStyle(styleBottom);
            }else {
                cell.setStyle(styleMiddle);
            }
            //note
            cell = sheet.getCells().get("F" + (channel.getNumber() + 1));
            cell.setValue(channel.getNote());
            if (isBottom){
                cell.setStyle(styleBottomRight);
            }else {
                cell.setStyle(styleRight);
            }

        }

        return workbook;
    }
}
