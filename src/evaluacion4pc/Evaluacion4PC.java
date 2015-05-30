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
        int i,j;
        int sueldoLiquido[]=new int[20];     
        int ventasMax[]=new int[20];
        
        int menor=04, segundoMenor=0,vendedorMenor=0,vendedorSegundoMenor=0;
        int vendedor,opcion,monto,mayorVenta=0,vendedorMayorVenta=0, descuentos;   
        double comision;
        int sueldoBase;
        int contadorVentas[]=new int[20];
        int totalVentas[]=new int[20];
        int cuentaCategoria[]= new int[4]; // 0: Def, 1:Regular, 2:Bueno, 3:Excelente
        
        System.out.println("Sueldo Base para todos los vendedores");
        
       ////////////// SUELDO BASE Y VALIDACIÓN ////////////////////
        // <editor-fold defaultstate="collapsed">
        
        sueldoBase = 0;
        while (sueldoBase <= 350000 || sueldoBase >= 450000) {
            System.out.println("Ingrese sueldo base para los trabajadores ($350.001 - $449.999)");
            sueldoBase=entrada.nextInt();
            if (sueldoBase <= 350000 || sueldoBase >= 450000) {
                System.out.println("Error, sueldo base debe ser entre $350.001 y $449.999");
            }
        }

        // </editor-fold>
        ///////////////////////////////////////////////////////////
        
        /////////////NUMERO MAXIMO DE VENTAS POR VENDEDOR Y VALIDACIÓN //////////
        // <editor-fold defaultstate="collapsed">
        
        System.out.println("Numero de ventas para cada uno de los 20 vendedores");

        i=0;
        while(i<20){

            ventasMax[i]=0;
            while(ventasMax[i]<5 || ventasMax[i]>20){
                System.out.println("Ingrese numero maximo de ventas a realizar por el vendedor "+(i+1)+": (5 - 20)");
                ventasMax[i]= entrada.nextInt();
                
                if(ventasMax[i]<5 || ventasMax[i]>20){
                    System.out.println("ERROR , las ventas maximas deben ser entre 5 y 20");
                }                
            }
            i++;
        }
        
        // </editor-fold>
        /////////////////////////////////////////////////////////////////////////
        
        
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
            
            vendedor--; // Acomodar para uso propio
            
            if(contadorVentas[vendedor]==ventasMax[vendedor]){
                
                System.out.println("El vendedor "+(vendedor+1)+ "alcanzo el maximo de ventas ("+ventasMax[vendedor]+")");    
                
            }
            else{
                
                ///////////// INGRESO MONTO Y VALIDACION /////////////////////
                // <editor-fold defaultstate="collapsed">
                
                monto = 0;
                while(monto<10000 || monto>150000){
                    System.out.println("Ingrese monto de venta del vendedor "+(vendedor+1) + " ($10.000 y $150.000)");
                    monto=entrada.nextInt();
                    if(monto<10000 || monto>150000){
                        System.out.println("Monto erroneo debe ser entre $10.000 y $150.000)");
                    }
                }
                
                //</editor-fold>
                //////////////////////////////////////////////////////////////
                
                if(monto>mayorVenta){
                    mayorVenta=monto;
                    vendedorMayorVenta=vendedor;
                }     
                
                totalVentas[vendedor]=totalVentas[vendedor]+monto;               
                contadorVentas[vendedor]++;   
                
            }
            /////// INGRESO DE OPCION Y VALIDACION/////////
            // <editor-fold defaultstate="collapsed">
            opcion=0;
            while(opcion!=1 && opcion!= 2){
                System.out.println("¿Desea agregar mas ventas? (1-2)");
                System.out.println("1) Si");
                System.out.println("2) No");
                opcion=entrada.nextInt();
                
                if(opcion!=1 && opcion!=2){
                    System.out.println("Ingrese una opcion valida (1-2)");
                }
            }
            // </editor-fold>
            ///////////////////////////////////////////////
        }
        

        i=0;
        while(i<20){
            comision=0;
            
            if(totalVentas[i]<100000){
                comision=0;
                cuentaCategoria[0]++;
            }else{
                
                if(totalVentas[i]<250000){
                    comision=0.03;
                    cuentaCategoria[1]++;
                }else{
                    if(totalVentas[i]<400000){
                        comision=0.07;
                        cuentaCategoria[2]++;
                    }else{
                        comision=0.15;
                        cuentaCategoria[3]++;
                    }
                }
                
            }
            
            sueldoLiquido[i]= sueldoBase + (int)( totalVentas[i]*comision );
            descuentos= (int) (sueldoLiquido[i]*0.17); //Trunco y fuerzo el entero (int)
            sueldoLiquido[i]= sueldoLiquido[i] - descuentos;
 
            i++;
        }
        
        ////////////////////////////////// BUSCAR 2 MENORES //////////////////////////////////////////////////////////
        int indexArreglo[]={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        
        ////////ORDENAR DE MENOR A MAYOR ARREGLO DE SUELDOS LIQUIDOS EN PARALLELO CON EL DE INDICES/////
        // <editor-fold defaultstate="collapsed" >
        int aux,indexAux;
        i = 0;
        while (i < 20) {
            j = 0;
            while (j < 19) {
                
                if (sueldoLiquido[j] > sueldoLiquido[j + 1]) {
                    aux = sueldoLiquido[j];                   
                    sueldoLiquido[j] = sueldoLiquido[j + 1];
                    sueldoLiquido[j + 1] = aux;
                    
                    indexAux=indexArreglo[j];
                    indexArreglo[j]=indexArreglo[j+1];
                    indexArreglo[j+1]=indexAux;
                }
                
                j++;
            }
            i++;
        }
        // </editor-fold>
        ////////////////////////////////////////////////////////////////////////////////////////////////
        
        menor=sueldoLiquido[0];
        segundoMenor=sueldoLiquido[1];
        vendedorMenor=indexArreglo[0];
        vendedorSegundoMenor=indexArreglo[1];
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
        System.out.println("MOSTRANDO RESULTADOS");
        System.out.println("Mayor venta individual: "+mayorVenta);
        System.out.println("Vendedor que realizo la mayor venta: "+(vendedorMayorVenta+1));
        System.out.println("Los 2 vendedores con menor sueldo liquido fueron: ");
        System.out.println("Vendedor "+(vendedorMenor+1)+": "+menor);
        System.out.println("Vendedor "+(vendedorSegundoMenor+1)+": "+segundoMenor);
        System.out.println("Cantidad de vendedores por categoria: (Vendedores sin ventas tambien fueron contabilizados)");
        
        i=0;
        while(i<4){
            System.out.println("Categoria "+ (i+1) +": "+ cuentaCategoria[i] + " Vendedores");
            i++;
        }
        
        
    }
    
}
