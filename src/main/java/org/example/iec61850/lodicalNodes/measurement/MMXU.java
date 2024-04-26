package org.example.iec61850.lodicalNodes.measurement;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.Filter.Filter;
import org.example.iec61850.Filter.Fourier;
import org.example.iec61850.common.modelData.Vector;
import org.example.iec61850.lodicalNodes.LN;
import org.example.iec61850.node_parameters.DataObject.CMV;
import org.example.iec61850.node_parameters.DataObject.measured_and_metered_values.DEL;
import org.example.iec61850.node_parameters.DataObject.measured_and_metered_values.MV;
import org.example.iec61850.node_parameters.DataObject.measured_and_metered_values.WYE;
import org.example.iec61850.node_parameters.DataObject.settings.ENG;

@Getter
@Setter
public class MMXU extends LN {
    /**
     * LN: Measurement Name: MMXU (LN: Название измерения: MMXU)
     *
     * Получать значения от CTs и VTs и вычислять измеряемые величины, такие как среднеквадратичные значения
     * тока и напряжения или потоки мощности из полученных выборок напряжения и тока. Эти значения обычно
     * используются для оперативных целей, таких как контроль и управление потоком мощности, отображение
     * на экране, оценка состояния и т.д. Должна быть обеспечена требуемая точность для этих функций.
     * (61850-5 - IEC: 2013)
     *
     * Функциональный класс LN: LN MMXU
     * Процедуры измерения в устройствах защиты являются частью специального алгоритма защиты,
     * представленного логическими узлами Xyz. Алгоритмы защиты, как и любая функция, выходят за рамки
     * стандарта связи. Поэтому LN Mxyz не должен использоваться в качестве входных данных для Pxyz.
     * Данные, связанные с неисправностью, такие как пиковое значение неисправности и т.д.,
     * всегда предоставляются строками типа Xyz, а не заимствованиями типа Xyz.
     */
    /**
     * Measured and metered values
     */
    private MV TotW = new MV();
    private MV TotWVAr = new MV();
    private MV TotVA = new MV();
    private MV TotPF = new MV();
    private MV Hz = new MV();
    private DEL PPV = new DEL();
    private WYE PhV = new WYE();

    private WYE W = new WYE();
    private WYE VAr = new WYE();
    private WYE VA = new WYE();
    private WYE PF = new WYE();
    private WYE Z = new WYE();
    private MV AvAPhs = new MV();
    private MV AvPPVPhs = new MV();
    private MV AvPhVPhs = new MV();
    private MV AvWPhs = new MV();
    private MV AvVAPhs = new MV();
    private MV AvVArPhs = new MV();
    private MV AvPFPhs = new MV();
    private MV AvZPhs = new MV();
    private MV MaxAPhs = new MV();
    private MV MaxPPVPhs = new MV();
    private MV MaxPhVPhs = new MV();
    private MV MaxWPhs = new MV();
    private MV MaxVAPhs = new MV();
    private MV MaxVArPhs = new MV();
    private MV MaxPFPhs = new MV();
    private MV MaxZPPhs = new MV();
    private MV MinAPhs = new MV();
    private MV MinPPVPhs = new MV();
    private MV MinPhVPhs = new MV();
    private MV MinWPhs = new MV();
    private MV MinVAPhs = new MV();
    private MV MinVArPhs = new MV();
    private MV MinPFPhs = new MV();
    private MV MinZPPhs = new MV();
    /**
     * Setting
     */
    private ENG ClcTotVA = new ENG();
    private ENG PFSign = new ENG();

    /**
     * Size buffer
     */
    public static int bufSize = 80;
    /**
     * Input
     */
    public MV IaInst = new MV();
    public MV IbInst = new MV();
    public MV IcInst = new MV();
    public MV UaInst = new MV();
    public MV UbInst = new MV();
    public MV UcInst = new MV();

    /**
     * Output
     */
    private WYE A = new WYE();
    private WYE PNV = new WYE();
    /**
     * Filter (буферы на каждую фазу тока и напряжения)
     */
    public final Filter ia = new Fourier(bufSize);
    public final Filter ib = new Fourier(bufSize);
    public final Filter ic = new Fourier(bufSize);
    public final Filter ua = new Fourier(bufSize);
    public final Filter ub = new Fourier(bufSize);
    public final Filter uc = new Fourier(bufSize);

    @Override
    public void process() {
        this.ia.process(this.IaInst, this.A.getPhsA());
        this.ib.process(this.IbInst, this.A.getPhsB());
        this.ic.process(this.IcInst, this.A.getPhsC());

        this.ua.process(this.UaInst, this.PNV.getPhsA());
        this.ub.process(this.UbInst, this.PNV.getPhsB());
        this.uc.process(this.UcInst, this.PNV.getPhsC());

        /**Расчет междуфазных векторов*/
        Vector valAB = resultVector(this.PNV.getPhsA(), this.PNV.getPhsB());
        Vector valBC = resultVector(this.PNV.getPhsB(), this.PNV.getPhsC());
        Vector valCA = resultVector(this.PNV.getPhsC(), this.PNV.getPhsA());

        this.PPV.getPhsAB().setInstCVal(valAB);
        this.PPV.getPhsBC().setInstCVal(valBC);
        this.PPV.getPhsCA().setInstCVal(valCA);
    }

    /**
     * Возвращает длину и фазу междуфазного вектора
     */
    public Vector resultVector(CMV valFirst, CMV valSecond) {
        double[] valFir = vectorRotation(valFirst, 0);
        double[] valSec = vectorRotation(valSecond, 180);

        double resX = valFir[0] + valSec[0];
        double resY = valFir[1] + valSec[1];

        /**Создание вектора*/
        Vector vector = new Vector();
        //Амплитуда вектора
        vector.getMag().getFloatVal().setValue(
                Math.sqrt(Math.pow(resX, 2) + Math.pow(resY, 2))
        );
        //Фаза вектора
        vector.getAng().getFloatVal().setValue(
                Math.atan(resY / resX) * (180 / Math.PI)
        );
        return vector;
    }

    /**
     * Возвращает длины вектора по оси x и по оси y
     */
    public double[] vectorRotation(CMV val, int degree) {
        double[] massVal = new double[2];
        /**Расчет проекций вектора на оси*/
        //X
        massVal[0] = val.getInstCVal().getMag().getFloatVal().getValue() *
                Math.cos((val.getInstCVal().getAng().getFloatVal().getValue() + degree) * Math.PI
                        / 180);
        //Y
        massVal[1] = val.getInstCVal().getMag().getFloatVal().getValue() *
                Math.sin((val.getInstCVal().getAng().getFloatVal().getValue() + degree) * Math.PI
                        / 180);
        return massVal;
    }
}
