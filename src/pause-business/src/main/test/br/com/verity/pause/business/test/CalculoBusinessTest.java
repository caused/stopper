package br.com.verity.pause.business.test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.verity.pause.business.CalculoBusiness;
import br.com.verity.pause.entity.ApontamentoPivotEntity;
import br.com.verity.pause.test.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class CalculoBusinessTest {

	private static final int ABRIL = 4;
	
	@Autowired
	private CalculoBusiness calculoBusiness;
	
	private Date obterData(int month, int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(2017, month-1, day);
		
		return new Date(calendar.getTimeInMillis());
	}
	
	@Test
	public void testcalcularApontamento01042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 1));

		assertEquals(0d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento02042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 2));

		assertEquals(0d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento03042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 3));

		assertEquals(10d, apontamento.getTotalHoras(), 0.01);
		assertEquals(2d, apontamento.getHorasExtras(), 0.01);
		assertEquals(1.98d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento04042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 4));

		assertEquals(9.85d, apontamento.getTotalHoras(), 0.01);
		assertEquals(1.85d, apontamento.getHorasExtras(), 0.01);
		assertEquals(1.50d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento05042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 5));

		assertEquals(10.88d, apontamento.getTotalHoras(), 0.01);
		assertEquals(2.88d, apontamento.getHorasExtras(), 0.01);
		assertEquals(1.98d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento06042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 6));

		assertEquals(6.5d, apontamento.getTotalHoras(), 0.01);
		assertEquals(-1.5d, apontamento.getHorasExtras(), 0.01);
		assertEquals(4d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento07042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 7));

		assertEquals(8d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento08042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 8));

		assertEquals(11.48d, apontamento.getTotalHoras(), 0.01);
		assertEquals(11.48d, apontamento.getHorasExtras(), 0.01);
		assertEquals(1.98d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(11.48d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(12.50d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento09042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 9));

		assertEquals(13.98d, apontamento.getTotalHoras(), 0.01);
		assertEquals(19.58d, apontamento.getHorasExtras(), 0.01);
		assertEquals(4.98d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento10042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 10));

		assertEquals(7d, apontamento.getTotalHoras(), 0.01);
		assertEquals(-1d, apontamento.getHorasExtras(), 0.01);
		assertEquals(2d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(2d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(5d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento11042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 11));

		assertEquals(8.33d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0.33d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento12042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 12));

		assertEquals(8.58d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0.58d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento13042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 13));

		assertEquals(8.70d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0.70d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento14042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 14));

		assertEquals(0d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento15042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 15));

		assertEquals(0d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento16042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 16));

		assertEquals(1.98d, apontamento.getTotalHoras(), 0.01);
		assertEquals(2.78d, apontamento.getHorasExtras(), 0.01);
		assertEquals(1.98d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento17042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 17));

		assertEquals(9.37d, apontamento.getTotalHoras(), 0.01);
		assertEquals(1.37d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento18042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 18));

		assertEquals(8.47d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0.47d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento19042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 19));

		assertEquals(8.53d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0.53d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento20042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 20));

		assertEquals(8d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0d, apontamento.getHorasExtras(), 0.01);
		assertEquals(3d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento21042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 21));

		assertEquals(9d, apontamento.getTotalHoras(), 0.01);
		assertEquals(12.60d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(9d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(14.98d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento22042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 22));

		assertEquals(8d, apontamento.getTotalHoras(), 0.01);
		assertEquals(8d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento23042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 23));

		assertEquals(7d, apontamento.getTotalHoras(), 0.01);
		assertEquals(9.8d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento24042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 24));

		assertEquals(10d, apontamento.getTotalHoras(), 0.01);
		assertEquals(2d, apontamento.getHorasExtras(), 0.01);
		assertEquals(1.98d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento25042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 25));

		assertEquals(13.07d, apontamento.getTotalHoras(), 0.01);
		assertEquals(5.07d, apontamento.getHorasExtras(), 0.01);
		assertEquals(3d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento26042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 26));

		assertEquals(9.08d, apontamento.getTotalHoras(), 0.01);
		assertEquals(1.08d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento27042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 27));

		assertEquals(8.18d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0.18d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento28042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 28));

		assertEquals(8d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento29042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 29));

		assertEquals(0d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}

	@Test
	public void testcalcularApontamento30042017() {
		ApontamentoPivotEntity apontamento = calculoBusiness.calcularApontamento(897, obterData(ABRIL, 29));

		assertEquals(0d, apontamento.getTotalHoras(), 0.01);
		assertEquals(0d, apontamento.getHorasExtras(), 0.01);
		assertEquals(0d, apontamento.getTotalAdicionalNoturno(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAvisoTrabalhado(), 0.01);
		assertEquals(0d, apontamento.getTotalSobreAviso(), 0.01);
	}
}
