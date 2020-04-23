
package interpreterP1;

import java.util.Map;

public class Int extends Exp
{
    public int intElem;

    public Int(int i)
    {
        intElem = i;
    }

    public void printParseTree(String indent)
    {
        super.printParseTree(indent);
        String indent1 = indent+" ";
        IO.displayln(indent1 + indent1.length() + " " + intElem);
    }

    @Override
    public Val Eval(Map<String, Val> valMap) {
        return new IntVal(intElem);
    }
}