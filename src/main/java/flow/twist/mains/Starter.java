package flow.twist.mains;

import java.util.*;

class Starter {
  public static void main(String[] args) {
    List<String> argList = new ArrayList<String>(Arrays.asList(args));
    System.out.println(Arrays.toString(args));
    String ifdsSolver = argList.remove(0);
    String saLib = argList.remove(0);
    String taintAnalysis = argList.remove(0);
    int numCores = Integer.parseInt(argList.remove(0));
    int N = Integer.parseInt(argList.remove(0));
    args = new String[args.length-5];
    argList.toArray(args);
    if (ifdsSolver.equals("heros") && saLib.equals("soot")) {

      if (taintAnalysis.equals("fw")) new ClassForNameForwardsFromStringParams(numCores, N, args);
      else if (taintAnalysis.equals("bidi")) new ClassForNameBiDi(numCores, N, args);
      else if (taintAnalysis.equals("gen")) new GenericCallerSensitiveBiDi(numCores, N, args);

    } else if (ifdsSolver.equals("ra") && saLib.equals("soot")) {

      if (taintAnalysis.equals("fw")) new RA(numCores, N, args);
      else if (taintAnalysis.equals("bidi")) new BiDiRA(numCores, N, args);
      else if (taintAnalysis.equals("gen")) new GenBiDiRA(numCores, N, args);
      else if (taintAnalysis.equals("seq")) new RASEQ(numCores, N, args);

    } else {
      throw new IllegalArgumentException();
    }
  }
}
