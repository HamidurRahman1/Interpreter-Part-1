
package interpreterP1;

import java.util.Map;

public class Nil extends Exp
{
    public void printParseTree(String indent)
    {
        super.printParseTree(indent);
        String indent1 = indent+" ";
        IO.displayln(indent1 + indent1.length() + " nil");
    }

    @Override
    public Val Eval(Map<String, Val> valMap) {
        return new NilVal();
    }
}