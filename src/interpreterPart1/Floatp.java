
package interpreterPart1;

import java.util.Map;

public class Floatp extends Exp
{
    public float floatElem;

    public Floatp(float f)
    {
        floatElem = f;
    }

    public void printParseTree(String indent)
    {
        super.printParseTree(indent);
        String indent1 = indent+" ";
        IO.displayln(indent1 + indent1.length() + " " + floatElem);
    }

    @Override
    public Val Eval(Map<String, Val> valMap) {
        return new FloatVal(floatElem);
    }
}