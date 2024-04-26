package org.example.iec61850.lodicalNodes.measurement;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Vector;
import org.example.iec61850.lodicalNodes.LN;
import org.example.iec61850.node_parameters.DataObject.CMV;
import org.example.iec61850.node_parameters.DataObject.measured_and_metered_values.DEL;
import org.example.iec61850.node_parameters.DataObject.measured_and_metered_values.MV;
import org.example.iec61850.node_parameters.DataObject.measured_and_metered_values.SEQ;
import org.example.iec61850.node_parameters.DataObject.measured_and_metered_values.WYE;

@Getter
@Setter
public class MSQI extends LN {
    /**
     * LN: Sequence and imbalance Name: MSQI (LN: Название последовательности и дисбаланса: MSQ)
     * */
    /**
     * Measured and metered values
     */
    private SEQ SeqA = new SEQ(); //По току
    private SEQ SeqV = new SEQ(); //По напряжению

    private SEQ DQ0Seq = new SEQ();
    private WYE ImbA = new WYE();
    private MV ImbNgA = new MV();
    private MV ImbNgV = new MV();
    private DEL ImbPPV = new DEL();
    private WYE ImbV = new WYE();
    private MV ImbZroA = new MV();
    private MV ImbZroV = new MV();
    private MV MaximbA = new MV();
    private MV MaximbPPV = new MV();
    private MV MaximbV = new MV();

    /**
     * Inputs
     */
    public WYE I = new WYE();
    public WYE U = new WYE();

    @Override
    public void process() {
        /**Векторы фаз тока*/
        Vector IForward = resultVector(this.I.getPhsA(), this.I.getPhsB(), this.I.getPhsC(), 120, 0);
        Vector IReverse = resultVector(this.I.getPhsA(), this.I.getPhsB(), this.I.getPhsC(), 0, 120);
        Vector IZero = resultVector(this.I.getPhsA(), this.I.getPhsB(), this.I.getPhsC(), 240, 240);

        /**Векторы фаз напряжения*/
        Vector UForward = resultVector(this.U.getPhsA(), this.U.getPhsB(), this.U.getPhsC(), 120, 0);
        Vector UReverse = resultVector(this.U.getPhsA(), this.U.getPhsB(), this.U.getPhsC(), 0, 120);
        Vector UZero = resultVector(this.U.getPhsA(), this.U.getPhsB(), this.U.getPhsC(), 240, 240);

        /**Токи (магнитуда и фаза)*/
        //Прямая последовательность
        this.SeqA.getC1().getInstCVal().getMag().getFloatVal().setValue(IForward.getMag().getFloatVal().getValue());
        this.SeqA.getC1().getInstCVal().getAng().getFloatVal().setValue(IForward.getAng().getFloatVal().getValue());

        //Обратная последовательность
        this.SeqA.getC2().getInstCVal().getMag().getFloatVal().setValue(IReverse.getMag().getFloatVal().getValue());
        this.SeqA.getC2().getInstCVal().getAng().getFloatVal().setValue(IReverse.getAng().getFloatVal().getValue());

        //Нулевая последовательность
        this.SeqA.getC3().getInstCVal().getMag().getFloatVal().setValue(IZero.getMag().getFloatVal().getValue());
        this.SeqA.getC3().getInstCVal().getAng().getFloatVal().setValue(IZero.getAng().getFloatVal().getValue());

        /**Напряжения (магнитуда и фаза)*/
        //Прямая последовательность
        this.SeqV.getC1().getInstCVal().getMag().getFloatVal().setValue(UForward.getMag().getFloatVal().getValue());
        this.SeqV.getC1().getInstCVal().getAng().getFloatVal().setValue(UForward.getAng().getFloatVal().getValue());

        //Обратная последовательность
        this.SeqV.getC2().getInstCVal().getMag().getFloatVal().setValue(UReverse.getMag().getFloatVal().getValue());
        this.SeqV.getC2().getInstCVal().getAng().getFloatVal().setValue(UReverse.getAng().getFloatVal().getValue());

        //Нулевая последовательность
        this.SeqV.getC3().getInstCVal().getMag().getFloatVal().setValue(UZero.getMag().getFloatVal().getValue());
        this.SeqV.getC3().getInstCVal().getAng().getFloatVal().setValue(UZero.getAng().getFloatVal().getValue());
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

    /**
     * Возвращает результирующий вектор фаз
     */
    public Vector resultVector(CMV phsA, CMV phsB, CMV phsC, int degB, int degC) {
        /**Расчет составляющих по каждой фазе*/
        double[] valA = vectorRotation(phsA, 0);
        double[] valB = vectorRotation(phsB, 240 - degB);
        double[] valC = vectorRotation(phsC, 240 - degC);

        double resX = valA[0] + valB[0] + valC[0];
        double resY = valA[1] + valB[1] + valC[1];

        /**Создание вектора*/
        Vector vector = new Vector();
        //Амплитуда вектора
        vector.getMag().getFloatVal().setValue(
                Math.sqrt(
                        Math.pow(resX, 2) + Math.pow(resY, 2)
                ) / 3
        );
        //Фаза вектора
        vector.getAng().getFloatVal().setValue(
                Math.atan(resY / resX) * (180 / Math.PI)
        );
        return vector;
    }
}
