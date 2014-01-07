/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shijiex.casa.unb.invoke;

import java.lang.invoke.*;
//import java.lang.invoke.ConvertHandle.FilterHelpers;
/**
 *
 * @author shijiex
 */
public class DynamicLinkageExample {
    private static MethodHandle helloHandler;

 private static void  helloWorld(String a, String b) {
  System.out.println("Hello dynamic linkage world! and your input are "+a+" =>"+b);
  //return "asdsa";
 }

 
 public static CallSite bootstrapDynamic(MethodHandles.Lookup caller, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException {
  MethodHandles.Lookup lookup = MethodHandles.lookup();
  System.out.println("The method name is "+name+" and methodType: "+type.toMethodDescriptorString());
  helloHandler= lookup.findStatic(lookup.lookupClass(), name, MethodType.methodType(void.class, String.class, String.class));
//  Build composition MH.   
//  MethodHandle insertMH = MethodHandles.insertArguments(helloHandler, 0, null);
//  return new ConstanceCallSite(insertMH.asType(type));
  
  
  /**
   *   The type here is ()V, but the helloHandle.type is ()i. I do this intentionally since I want to wrapper multiple MHs together
   *   Based on asType implemenation: 
   *   helloHandler.type().equal(type)  => return ConstanceCallSite(helloHandler);
   *   !helloHandler.type().equal(type)  => return ConstanceCallSite(AsTypeHandle(FilterReturnHandle(getPrimitiveReturnFilter(filterType, isExplicitCast))));
   *   
   *   getPrimitiveReturnFilter(filterType, isExplicitCast) is defined in ConvertHandle.java and handle is cached in cachedReturnFilters(ref: getPrimitiveReturnFilter in ConvertHandle) 
   */
  
  //MethodHandle newHander = helloHandler.asType(type);
  
//  if(helloHandler.type() == type ){
//	  //asType returns hellHandler itself. => 1 MH 
//	  System.out.println("  One MH ");
//  }else{
//	  //Chains exists 
//	  System.out.println("Multople MHS: input type: "+type.toMethodDescriptorString());
//  }
  
  //return new MutableCallSite(helloHandler.asType(type));
  
  MethodHandle lowcase = lookup.findVirtual(String.class, "toLowerCase", MethodType.methodType(String.class));
  MethodHandle upcase = lookup.findVirtual(String.class, "toUpperCase", MethodType.methodType(String.class));
  return new MutableCallSite(MethodHandles.filterArguments(helloHandler, 0, lowcase,upcase).asType(type));
 }
  
// public static CallSite getStringBootstrap(MethodHandles.Lookup caller, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException{
//     MethodHandles.Lookup lookup = MethodHandles.lookup();
//     //System.out.println("This is getStringBootStrap method ");
//     helloHandler = lookup.findStatic(lookup.lookupClass(), name, MethodType.methodType(String.class, Integer.class, Integer.class));
//     return new MutableCallSite(helloHandler.asType(type));
// }
         
}
