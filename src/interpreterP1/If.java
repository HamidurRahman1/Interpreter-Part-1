
package interpreterP1;

import java.util.Map;

public class If extends Exp
{
    Exp exp1;
    Exp exp2;
    Exp exp3;

    If(Exp e1, Exp e2, Exp e3)
    {
        exp1 = e1;
        exp2 = e2;
        exp3 = e3;
    }

    void printParseTree(String indent)
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
    Val Eval(Map<String, Val> map)
    {
        System.out.println(exp1.Eval(map) + " " + exp1.getClass());
        System.out.println(exp2.Eval(map) + " " + exp2.getClass());
        System.out.println(exp3.Eval(map) + " " + exp3.getClass());

        return null;
    }
}