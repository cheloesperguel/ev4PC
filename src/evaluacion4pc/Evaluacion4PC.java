/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluacion4pc;

import java.util.Scanner;

public class Evaluacion4PC {
    static Scanner entrada = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int sueldoLiquido[]=new int[20];
        int c=0;
        int ventasVendedores[][]=new int[20][20];
        int ventas[]=new int[20];
        int vendedor,opcion,monto,mayorVenta=0,vendedorMayorVenta;
        double comision;
        int sueldoBase[] = new int[20];
        int contadorVentas[]=new int[20];
        int totalVentas[]=new int[20];
        int categoriaVendedor[]= new int[20];
        
        System.out.println("Sueldo Base y Numero de ventas para cada uno de los 20 vendedores");
        
        int i=0;
        while(i<20){
            
            sueldoBase[i]=0;
            while(sueldoBase[i]<=350000 || sueldoBase[i]>=450000){
                System.out.println("Ingrese sueldo base para el trabajador "+ (i+1) +" ($350.000 - $450.000)");
                
                if(sueldoBase[i]<=350000 || sueldoBase[i]>=450000){
                    System.out.println("Error, sueldo base debe ser entre $350.000 y $450.000");
                }
            }
            
            
            ventas[i]=0;
            while(ventas[i]<5 || ventas[i]>20){
                System.out.println("Ingrese numero ventas realizadas por el vendedor "+(i+1)+": (5 - 20)");
                ventas[i]= entrada.nextInt();
                
                if(ventas[i]<5 || ventas[i]>20){
                    System.out.println("ERROR , las ventas deben ser entre 5 y 20");
                }                
            }
            i++;
        }
        
        System.out.println("Ingreso de ventas:");
        
        opcion=0;
        while(opcion !=2){
            
            vendedor=0;
            while(vendedor<1 || vendedor>20){
                System.out.println("Ingrese vendedor: (1-20)");
                vendedor=entrada.nextInt();
                if(vendedor<1 || vendedor>20){
                    System.out.println("Error ingrese vendedor entre 1 y 20");
                }
            }
            
            
            
            if(contadorVentas[vendedor-1]==ventas[vendedor-1]){
                System.out.println("Vendedor "+(vendedor)+ "alcanzo el maximo de ventas ("+ventas[vendedor-1]+")");
            
            }else{
                
                monto = 0;
                while(monto<10000 && monto>150000){
                    System.out.println("Ingrese monto de venta del vendedor "+vendedor + " ($10.000 y $150.000");
                    monto=entrada.nextInt();
                    if(monto<10000 && monto>150000){
                        System.out.println("Monto erroneo debe ser entre $10.000 y $150.000");
                    }
                }
                if(monto>mayorVenta){
                    mayorVenta=monto;
                    vendedorMayorVenta=vendedor-1;
                }
                    
                
                ventasVendedores[vendedor-1][contadorVentas[vendedor-1]]=monto;
                contadorVentas[vendedor-1]++;   
            }
            
            
            while(opcion!=1 && opcion!= 2){
                System.out.println("¿Desea agregar mas ventas? (1-2)");
                System.out.println("1) Si");
                System.out.println("2) No");
                opcion=entrada.nextInt();
                
                if(opcion!=1 && opcion!=2){
                    System.out.println("Ingrese una opcion valida (1-2)");
                }
            }
        }
        

        
        
        i=0;
        while(i<20){
            comision=0;
            c=0;
            while(c<contadorVentas[i]){
                totalVentas[i]=totalVentas[i]+ventasVendedores[i][c];
                c++;
            }
            
            if(totalVentas[i]<100000){
                comision=0;
                categoriaVendedor[i]=1;
            }else{
                
                if(totalVentas[i]<250000){
                    comision=0.03;
                    categoriaVendedor[i]=2;
                }else{
                    if(totalVentas[i]<400000){
                        comision=0.07;
                        categoriaVendedor[i]=3;
                    }else{
                        comision=0.15;
                        categoriaVendedor[i]=4;
                    }
                }
                
            }
            
            sueldoLiquido[i]= sueldoBase[i] + (int)( sueldoBase[i]*comision );            
            sueldoLiquido[i]= sueldoLiquido[i] - (int) (sueldoLiquido[i]*0.17);
            
            
            i++;
        }
        
        
        
    }
    
}
