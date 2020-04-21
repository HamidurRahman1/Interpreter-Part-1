
package interpreterP1;

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
        setIO("/Users/hamidurrahman/Downloads/GitHub/Project--Interpreter-Part-1/src/inputs/in2.txt",
                "/Users/hamidurrahman/Downloads/GitHub/Project--Interpreter-Part-1/src/outputs/out1.txt");
        setLex();

        getToken();
        FunDefList funDefList = funDefList();
        if ( ! t.isEmpty() )
            errorMsg(0);
        if ( syntaxErrorFound )
        {
            closeIO();
            setIO("/Users/hamidurrahman/Downloads/GitHub/Project--Interpreter-Part-1/src/inputs/in2.txt",
                "/Users/hamidurrahman/Downloads/GitHub/Project--Interpreter-Part-1/src/outputs/out2.txt");
            getToken();
            Exp exp = exp();
            if ( ! t.isEmpty() )
                displayln(t + "  -- unexpected symbol");
            else if ( syntaxErrorFound )
            {
                Val v = exp.Eval(new HashMap<String,Val>());  // evaluate the given expression
                if ( v != null )
                    System.out.println( v.toString() );   // display the value on the terminal
            }
        }
        closeIO();
    }
}