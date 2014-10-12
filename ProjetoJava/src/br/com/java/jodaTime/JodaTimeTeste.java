package br.com.java.jodaTime;

import java.util.Locale;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.Years;

/**
 *
 * @author CRISTIANO
 */
public class JodaTimeTeste {

    public static void main(String[] args) {

        DateTime data = new DateTime();
        DateTime data2 = new DateTime(2016, 4, 5, 0, 0);
        System.out.println(data);//data atual
        System.out.println(data.getDayOfMonth());//dia do mês
        System.out.println(data.getHourOfDay());//hora do dia
        System.out.println(data.getMonthOfYear());//mês atual
        System.out.println(data.monthOfYear().getAsText());//mês atual
        System.out.println(data.monthOfYear().getAsText(Locale.ENGLISH));//mês atual ingles
        System.out.println(data.dayOfWeek().getAsText());//dia da semana atual
        System.out.println(data.year().getAsText());//ano atual
        System.out.println(data.yearOfCentury().getAsText());//ano do seculo
        Days d = Days.daysBetween(data, data2);
        System.out.println(d.getDays());//comparação de dias
        Years y = Years.yearsBetween(data, data2);
        System.out.println(y.getYears());//comparação de ano

        Interval intervalo = new Interval(data, data2);
        Duration duracao = intervalo.toDuration();
        System.out.println(duracao.getMillis());//duração do intervalo em milissegundos

        Period periodo = intervalo.toPeriod();
        System.out.println(periodo.getYears());//periodo entre duas datas
        System.out.println(periodo.getMonths());//periodo em meses
        System.out.println(periodo.getDays());//periodo entre dias

    }

}
