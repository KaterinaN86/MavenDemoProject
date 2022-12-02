package org.example;

/**
 * main method prints application name using static method from ModuleOneDemo project
 */
public class ModuleTwoApp
{
    /**
     * Method to be called from parent project
     */
    public static void printAppName(){
        System.out.println(ModuleOneApp.getAppName());
    }
    public static void main(String[] args){
        System.out.println(ModuleOneApp.getAppName());
    }

}
