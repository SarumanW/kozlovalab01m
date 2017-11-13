package excel;

import analyzer.Reflection;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class, where working with apache.poi library is going.
 * Provides data export to the excel document and building
 * of linechart.
 */
public class Export {
    /**
     * Export data about methods execution time in a
     * table view, draws linechart into his document.
     * @param data to be exported
     */
    public void export(List<Object[]> data) {
        Reflection reflection = new Reflection();
        int sortCount = reflection.getSortCount();
        try {
            File excel = new File("F://save/netcracker/kozlovalab01m/data.xlsx");
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheetAt(0);

            List<String> sortNames = getSortNames(data);
            List<String> fillerNames = getFillerNames(data);
            List<Long> sortTimes = getSortTimes(data);

            int rowNum = 0;
            int cellFirstNum = 0;

            Row firstRow = sheet.createRow(rowNum++);
            Cell cellEmpty = firstRow.createCell(cellFirstNum++);
            cellEmpty.setCellValue("");
            for(String s : sortNames){
                Cell cell = firstRow.createCell(cellFirstNum++);
                cell.setCellValue(s);
            }

            for(int i = 0; i < fillerNames.size(); i++){
                Row row = sheet.createRow(rowNum++);
                Cell firstCell = row.createCell(0);
                firstCell.setCellValue(fillerNames.get(i));

                int cellNum = 1;
                for(int j = sortCount * i; j < sortCount * (i + 1); j++){
                    Cell cell = row.createCell(cellNum++);
                    cell.setCellValue(sortTimes.get(j));
                }
            }

            drawChart(sheet);

            FileOutputStream os = new FileOutputStream(excel);
            book.write(os);
            System.out.println("Writing on Excel file Finished");

            os.close();
            book.close();
            fis.close();

        } catch(FileNotFoundException fe){
            fe.printStackTrace();
        } catch (IOException ie){
            ie.printStackTrace();
        }
    }

    /**
     * Method for getting string names of sort methods.
     * @param data data to find names
     * @return list of names
     */
    private List<String> getSortNames(List<Object[]> data){
        Reflection reflection = new Reflection();
        int sortCount = reflection.getSortCount();
        List<String> sortNames = new ArrayList<>();
        for(int i = 0; i < sortCount; i++){
            Object[] objectArray = data.get(i);
            sortNames.add((String)objectArray[1]);
        }
        return sortNames;
    }

    /**
     * Method for getting string names of filler methods.
     * @param data data to find names
     * @return list of names
     */
    private List<String> getFillerNames(List<Object[]> data){
        Reflection reflection = new Reflection();
        int sortCount = reflection.getSortCount();
        List<String> fillerNames = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            Object[] objectArray = data.get(i);
            if(i%sortCount == 0){
                fillerNames.add((String)objectArray[0]);
            }
        }
        return fillerNames;
    }

    /**
     * Method for getting string names of sort methods execution time.
     * @param data data to find time
     * @return list of time
     */
    private List<Long> getSortTimes (List<Object[]> data){
        List<Long> sortTimes = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            Object[] objectArray = data.get(i);
            sortTimes.add((Long)objectArray[2]);
        }
        return sortTimes;
    }

    /**
     * Draws linechart into excel document to show
     * how fast(slow) are sorting methods.
     * @param sheet sheet where chart is drawing
     */
    private void drawChart(Sheet sheet){
        Drawing<?> drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);

        Chart chart = drawing.createChart(anchor);
        ChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        LineChartData lineChartData = chart.getChartDataFactory().createLineChartData();

        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, 6));
        ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, 6));
        ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, 6));
        ChartDataSource<Number> ys3 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(3, 3, 0, 6));
        ChartDataSource<Number> ys4 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(4, 4, 0, 6));


        lineChartData.addSeries(xs, ys1);
        lineChartData.addSeries(xs, ys2);
        lineChartData.addSeries(xs, ys3);
        lineChartData.addSeries(xs, ys4);

        chart.plot(lineChartData, bottomAxis, leftAxis);
    }
}
