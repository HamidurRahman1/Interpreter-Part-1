
package interpreterP1;

import java.util.Map;

abstract class Exp
{
    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <exp>");
    }

    abstract Val Eval(Map<String, Val> valMap);
}