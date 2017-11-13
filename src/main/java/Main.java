import analyzer.Analyze;
import excel.Export;

import java.util.List;


public class Main {
        public static void main(String[] args) throws ReflectiveOperationException {
            Analyze analyzer = new Analyze();
            Export exporter = new Export();

            List<Object[]> dataList = analyzer.loadMethods();
            exporter.export(dataList);
        }
}
