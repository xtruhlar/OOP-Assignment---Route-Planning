//package com.example.MAIN;

import java.io.FileWriter;
import java.io.IOException;

//public aspect ErrorLoggerAspect{
//
//  private FileWriter errorLogWriter;
//
//        // Bod s rezným slovom pre všetky metódy s názvom začínajúcim na "error" alebo "exception"
//        pointcut error():execution(**(..))&&!within(ErrorLoggerAspect)&&(call(*.error*(..))||call(*.exception*(..)));
//
//        // Pred metódou, ktorá spadá do bodu "error", otvoríme súbor "error_log.txt" a zapíšeme doň záznam o chybe
//        before():error(){
//            try{
//                  errorLogWriter=new FileWriter("error_log.txt",true);
//                  errorLogWriter.write("Chyba sa stala o "+new java.util.Date()+"\n");
//              }catch(IOException e){
//                  System.err.println("Chyba pri zápise do súboru error_log.txt");
//              }
//            }
//
//        // Po metóde, ktorá spadá do bodu "error", uzavrieme súbor
//        after():error(){
//              try{
//                  errorLogWriter.close();
//              }catch(IOException e){
//                   System.err.println("Chyba pri zatváraní súboru error_log.txt");
//              }
//            }
//
//        }