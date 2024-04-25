package org.example.iec61850.lodicalNodes.system_logic_nodes;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.lodicalNodes.LN;
import org.example.iec61850.node_parameters.DataObject.measured_and_metered_values.MV;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
public class LSVS extends LN {
    private String path;
    private String fileName;
    private final List<MV> out = new ArrayList<>();
    private List<String> cfgFileList = new ArrayList<>();
    private List<String> csvFileList = new ArrayList<>();
    private int analogSignal = 0;
    private int digitalSignal = 0;
    private Iterator<String> scvIterator;

    public LSVS() {
        for (int i = 0; i < 20; i++) out.add(new MV());
    }


    @Override
    public void process() {
        if (this.scvIterator.hasNext()) {
            String[] str = this.scvIterator.next().split(",");

            for (int i = 0; i < this.analogSignal; i++) {
                double values = Double.parseDouble(str[i + 1]);
                this.out.get(i).getInstMag().getFloatVal().setValue(values * 1000);
            }
        }
    }

    public boolean hasNext() {
        return this.scvIterator.hasNext();
    }

    public void setFileName(String fileName) throws Exception {
        this.fileName = fileName;
        String csvPath = path + fileName + ".csv";
        String cfgPath = path + fileName + ".cfg";

        File csvFile = new File(csvPath);
        File cfgFile = new File(cfgPath);

        if (!csvFile.exists()) throw new Exception("Путь указан неверно!");
        if (!cfgFile.exists()) throw new Exception("Путь указан неверно!");

        this.csvFileList = Files.readAllLines(csvFile.toPath());
        this.cfgFileList = Files.readAllLines(cfgFile.toPath());

        String strNumber = this.cfgFileList.get(1)
                .replace("A", "")
                .replace("D", "");

        this.analogSignal = Integer.parseInt(strNumber.split(",")[1]);
        this.digitalSignal = Integer.parseInt(strNumber.split(",")[2]);

//        for (int i = 2; i < this.analogSignal + 2; i++) {
//            double kA = Double.parseDouble(this.cfgFileList.get(i).split(",")[5]);
//            double kB = Double.parseDouble(this.cfgFileList.get(i).split(",")[6]);
//
//            this.kAList.add(kA);
//            this.kBList.add(kB);
//        }
        this.scvIterator = this.csvFileList.iterator();
        if (this.scvIterator.hasNext()) {
            this.scvIterator.next();
        }
    }
    /**
     * LN: Sampled value subscription Name: LSVS (LN: Выборочное значение Имя подписки: LSVS)
     * */
    /**
     * Status information
     */
//    private SPS NdsCom = new SPS();
//    private SPS St = new SPS();
//    private SPS SimSt = new SPS();
//    private INS LastStNum = new INS();
//    private INS ConfRevNum = new INS();
    /**
     * Settings
     */
//    private ORG SvCBRef = new ORG();


}
