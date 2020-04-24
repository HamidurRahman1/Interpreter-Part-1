
package interpreterPart1;

import java.util.HashMap;

public abstract class Interpreter extends Parser
{
    public static void main(String[] args)
    {
        setIO(args[0], args[1]);
        setLex();
        getToken();
        FunDefList funDefList = funDefList();
        if (!t.isEmpty()) errorMsg(0);
        closeIO();

        setIO(args[2], args[3]);
        getToken();
        Exp exp = exp();
        if(!t.isEmpty()) displayln(t + "  -- unexpected symbol");
        Val v = exp.Eval(new HashMap<>());
        if(v != null) System.out.println(v.toString());
        closeIO();
    }

    private static void allInputChecker()
    {
        final String inputPath = "/Users/hamidurrahman/Downloads/GitHub/Project--Interpreter-Part-1/src/inputs/";
        final String outputPath = "/Users/hamidurrahman/Downloads/GitHub/Project--Interpreter-Part-1/src/outputs/";

        final int totalFiles = 29;

        for(int i=1; i<=totalFiles; i++)
        {
            System.out.print(i+". ");
            printResult(inputPath+"in"+i+".txt", outputPath+"out.txt");
        }
    }

    private static void printResult(String inputFilePath, String outputFilePath)
    {
        setIO(inputFilePath, outputFilePath);
        setLex();
        getToken();

        Exp exp = exp();
        if(!t.isEmpty()) displayln(t + "  -- unexpected symbol");

        Val v = exp.Eval(new HashMap<>());
        if(v != null) System.out.println(v.toString());
        closeIO();
    }
}