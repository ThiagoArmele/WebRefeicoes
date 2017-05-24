package webrefeicoes.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Locale.Category;

public class Util {

	public boolean isCpf(String cpf){
		
		if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
		        cpf.equals("22222222222") || cpf.equals("33333333333") ||
		        cpf.equals("44444444444") || cpf.equals("55555555555") ||
		        cpf.equals("66666666666") || cpf.equals("77777777777") ||
		        cpf.equals("88888888888") || cpf.equals("99999999999")) {
		 return false;
		} else {
			int dig10 = Integer.parseInt(cpf.substring(9,10));
			int dig11 = Integer.parseInt(cpf.substring(10));
			int resultadoCpfPrimeiroDigito = 0, resultadocpfSegundooDigito = 0;
			int peso = 10;
			
			for (int i = 0; i < cpf.length()-2; i++) {
				resultadoCpfPrimeiroDigito += (peso * Integer.parseInt(cpf.substring(i,i+1)));
				peso--;
			}
			
			peso = 11;
			for (int i = 0; i < cpf.length()-1; i++) {
				resultadocpfSegundooDigito += (peso * Integer.parseInt(cpf.substring(i,i+1)));
				peso--;
			}
			
			int calculoResultadoCpfPrimeiroDigito = 11-(resultadoCpfPrimeiroDigito % 11);
			int calculoResultadoCpfSegundoDigito = 11-(resultadocpfSegundooDigito % 11);
			if ((calculoResultadoCpfPrimeiroDigito == dig10 && calculoResultadoCpfSegundoDigito == dig11)
					|| (calculoResultadoCpfPrimeiroDigito == 11 && dig10 == 0)
					|| (calculoResultadoCpfPrimeiroDigito == 10 && dig10 == 0)
					|| (calculoResultadoCpfSegundoDigito == 10 && dig11 == 0)
					|| (calculoResultadoCpfSegundoDigito == 11 && dig11 == 0)) {
				return true;
			}
			
		 }
		return false;
	}
	
//	public double converteDouble(double d) throws ParseException{
//		
//		Locale fmtLocale = Locale.getDefault(Category.FORMAT);
//		DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance(fmtLocale);
//		format.setMaximumFractionDigits(2);
//		format.setMinimumFractionDigits(2);
//		
//		return (double) format.parse(d);
//		
//	}
	
}
