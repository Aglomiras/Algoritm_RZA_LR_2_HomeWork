package org.example.iec61850.lodicalNodes.function_protection;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.lodicalNodes.LN;
import org.example.iec61850.node_parameters.DataObject.measured_and_metered_values.SEQ;
import org.example.iec61850.node_parameters.DataObject.settings.ASG;
import org.example.iec61850.node_parameters.DataObject.settings.ENG;
import org.example.iec61850.node_parameters.DataObject.status_information.ACD;

@Getter
@Setter
public class RDIR extends LN {
    /**
     * LN: Directional element Name: RDIR (LN: Имя элемента направления: RDIR)
     *
     * Этот LN должен использоваться для представления всех объектов данных о направлении в выделенном LN,
     * используемом для настройки ретрансляции направления. Сама функция защиты моделируется специальным модулем защиты
     * LN. LN RDIR может использоваться с функциями 21, 32 или 67 в соответствии с
     * обозначением функционального номера устройства IEEE.
     * */

    /**
     * Status information
     */
    private ACD Dir = new ACD(); //Направление (boolean)

    /**
     * Settings
     */
    private ASG ChrAng = new ASG(); //Характеристика угла
    private ASG MinFwdAng = new ASG(); //Минимальный фазовый угол в прямом направлении
    private ASG MinRvAng = new ASG(); //Минимальный фазовый угол в обратном направлении
    private ASG MaxFwdAng = new ASG(); //Максимальный фазовый угол в прямом направлении
    private ASG MaxRvAng = new ASG(); //Максимальный фазовый угол в обратном направлении
    private ASG BlkValA = new ASG(); //Минимальный рабочий ток
    private ASG BlkValV = new ASG(); //Минимальное рабочее напряжение
    private ENG PolQty = new ENG(); //Поляризующее количество
    private ASG MinPPV = new ASG(); //Минимальное междуфазное напряжение

    /**
     * Inputs
     */
    public SEQ SeqA = new SEQ(); //По току
    public SEQ SeqV = new SEQ(); //По напряжению
    public Attribute<Double> S = new Attribute<>(); //Комплексная мощность

    /**Определение направления мощности*/
    @Override
    public void process() {
        //S = 3*I*U*cos(phsU-phsA)
        this.S.setValue(
                3 * this.SeqA.getC3().getInstCVal().getMag().getFloatVal().getValue()
                        * this.SeqV.getC3().getInstCVal().getMag().getFloatVal().getValue()
                        * Math.cos(this.SeqV.getC3().getInstCVal().getAng().getFloatVal().getValue() -
                        this.SeqA.getC3().getInstCVal().getAng().getFloatVal().getValue())
        );

        if (this.S.getValue() > 0) {
            this.Dir.getDirGeneralAttribute().setValue(ACD.dirGeneral.FORWARD); //Направление прямое
        } else {
            this.Dir.getDirGeneralAttribute().setValue(ACD.dirGeneral.BOTH); //Направление обратное
        }
    }
}
