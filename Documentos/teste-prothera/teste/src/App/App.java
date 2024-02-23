package App;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Funcionario;

public class App {
    public static void main(String[] args) {
    	 List<Funcionario> funcionarios = new ArrayList<>();
    	 Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
    	 BigDecimal salarioMinimo = new BigDecimal("1212.00");
    	 
    	 
    	 funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
         funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
         funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
         funcionarios.add(new Funcionario("Miguel", LocalDate.of(1882, 10, 14), new BigDecimal("19119.88"), "Diretor"));
         funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
         funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
         funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
         funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
         funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
         funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
         
        
         for (Funcionario funcionario : funcionarios) {
        	    if (funcionario.getNome().equals("João")) {
        	        funcionarios.remove(funcionario);
        	        break;
        	    }
        	}
         
         
         System.out.println("Lista de funcionários:");
         for (Funcionario funcionario : funcionarios) {
             System.out.println(funcionario);
         }
         
         aplicarAumentoSalario(funcionarios, new BigDecimal("10"));
         System.out.println();
         System.out.println("Lista de funcionários com aumento de 10% no salario:");
         for (Funcionario funcionario : funcionarios) {
             System.out.println(funcionario);
         }
         
         
         for (Funcionario funcionario : funcionarios) {
             String funcao = funcionario.getFuncao();
             List<Funcionario> funcionariosDaFuncao = funcionariosPorFuncao.getOrDefault(funcao, new ArrayList<>());
             funcionariosDaFuncao.add(funcionario);
             funcionariosPorFuncao.put(funcao, funcionariosDaFuncao);
         }
         System.out.println();
         
         System.out.println("imprimir os funcionários, agrupados por função.");
         for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
             System.out.println("Função: " + entry.getKey());
             for (Funcionario funcionario : entry.getValue()) {
                 System.out.println(funcionario);
             }
             System.out.println();
         }
         System.out.println();
         System.out.println("Imprimir os funcionários que fazem aniversário no mês 10 e 12.");
         for (Funcionario funcionario : funcionarios) {
             int mesAniversario = funcionario.getDataNascimento().getMonthValue();
             if (mesAniversario == 10 || mesAniversario == 12) {
                 System.out.println(funcionario);
             }
         }
         
         Collections.sort(funcionarios, Comparator.comparing(Funcionario::getNome));
         System.out.println();
         System.out.println("Lista de funcionários em ordem alfabética:");
         for (Funcionario funcionario : funcionarios) {
             System.out.println(funcionario);
         }
         
         
         BigDecimal totalSalarios = calcularTotalSalarios(funcionarios);
         System.out.println();
         System.out.println("Total dos salários dos funcionários: " + totalSalarios);
         
         System.out.println();
         System.out.println("Quantidade de salários mínimos que cada funcionário ganha:");
         for (Funcionario funcionario : funcionarios) {
             BigDecimal quantSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
             System.out.println(funcionario.getNome() + ": " + quantSalariosMinimos + " salários mínimos");
         }
     }    
    
    
    public static void aplicarAumentoSalario(List<Funcionario> funcionarios, BigDecimal percentualAumento) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal aumento = funcionario.getSalario().multiply(percentualAumento.divide(new BigDecimal("100")));
            BigDecimal novoSalario = funcionario.getSalario().add(aumento);
            funcionario.setSalario(novoSalario);
        }
    }
    
    public static BigDecimal calcularTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        return totalSalarios;
    }
     
}