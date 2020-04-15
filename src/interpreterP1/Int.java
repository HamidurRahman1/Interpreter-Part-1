
package interpreterP1;

import java.util.Map;

public class Int extends Exp
{
    int intElem;

    Int(int i)
    {
        intElem = i;
    }

    void printParseTree(String indent)
    {
        super.printParseTree(indent);
        String indent1 = indent+" ";
        IO.displayln(indent1 + indent1.length() + " " + intElem);
    }

    @Override
    Val Eval(Map<String, Val> map) {
        return null;
    }
}