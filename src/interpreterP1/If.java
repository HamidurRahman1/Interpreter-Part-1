
package interpreterP1;

import java.util.Map;

public class If extends Exp
{
    public Exp exp1;
    public Exp exp2;
    public Exp exp3;

    public If(Exp e1, Exp e2, Exp e3)
    {
        exp1 = e1;
        exp2 = e2;
        exp3 = e3;
    }

    public void printParseTree(String indent)
    {
        super.printParseTree(indent);

        String indent1 = indent+" ";
        String indent2 = indent1+" ";

        IO.displayln(indent1 + indent1.length() + " if");
        exp1.printParseTree(indent2);
        IO.displayln(indent1 + indent1.length() + " then");
        exp2.printParseTree(indent2);
        IO.displayln(indent1 + indent1.length() + " else");
        exp3.printParseTree(indent2);
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        if(exp1.Eval(valMap) instanceof BoolVal)
        {
            if(((BoolVal)exp1.Eval(valMap)).val) return exp2.Eval(valMap);
            else return exp3.Eval(valMap);
        }
        else
        {
            System.out.println("Error: boolean condition of if-then-else evaluated to non-boolean value: "+exp1.Eval(valMap));
            return null;
        }
    }
}