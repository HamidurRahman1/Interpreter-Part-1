
package interpreterP1;

import java.util.Map;

class Floatp extends Exp
{
    float floatElem;

    Floatp(float f)
    {
        floatElem = f;
    }

    void printParseTree(String indent)
    {
        super.printParseTree(indent);
        String indent1 = indent+" ";
        IO.displayln(indent1 + indent1.length() + " " + floatElem);
    }

    @Override
    Val Eval(Map<String, Val> valMap) {
        return new FloatVal(floatElem);
    }
}