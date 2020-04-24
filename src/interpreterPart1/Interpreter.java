
package interpreterPart1;

import java.util.HashMap;

public abstract class Interpreter extends Parser
{
    /*
	   argv[0]: source program file containing function definitions
	   argv[1]: lexical/syntactical error messages for the source program in argv[0]
	   argv[2]: single expression to be evaluated
	   argv[3]: lexical/syntactical error messages for the expression in argv[2]

	   The evaluation result and runtime errors will be displayed on the terminal.
	*/
    public static void main(String argv[])
    {
        allInputChecker();

//        setIO("/Users/hamidurrahman/Downloads/GitHub/Project--Interpreter-Part-1/src/inputs/in2.txt",
//                "/Users/hamidurrahman/Downloads/GitHub/Project--Interpreter-Part-1/src/outputs/out1.txt");
//
//        setLex();
//
//        getToken();
//
//        FunDefList funDefList = funDefList();
//        if (!t.isEmpty())
//            errorMsg(0);
//
//        closeIO();
//
//        setIO("/Users/hamidurrahman/Downloads/A-Spring2020/CSCI316/project3/ins/in29.txt",
//            "/Users/hamidurrahman/Downloads/GitHub/Project--Interpreter-Part-1/src/outputs/out2.txt");
//
//        getToken();
//
//        Exp exp = exp();
//
//        if(!t.isEmpty())
//            displayln(t + "  -- unexpected symbol");
//
//        Val v = exp.Eval(new HashMap<>());
//
//        if (v != null)
//            System.out.println(v.toString());
//
//        closeIO();
    }

    private static void allInputChecker()
    {
        final String inputPath = "/Users/hamidurrahman/Downloads/GitHub/Project--Interpreter-Part-1/src/inputs/";
        final String outputPath = "/Users/hamidurrahman/Downloads/GitHub/Project--Interpreter-Part-1/src/outputs/";

        final int totalFiles = 29;

        for(int i=1; i<=totalFiles; i++)
        {
            System.out.print(i+". ");
            printResult(inputPath+"in"+i+".txt", outputPath+"out"+i+".txt");
        }
    }

    private static void printResult(String inputFilePath, String outputFilePath)
    {
        setIO(inputFilePath, outputFilePath);

        setLex();

        getToken();

        Exp exp = exp();

        if(!t.isEmpty())
            displayln(t + "  -- unexpected symbol");

        Val v = exp.Eval(new HashMap<>());

        if (v != null)
            System.out.println(v.toString());

        closeIO();
    }
}