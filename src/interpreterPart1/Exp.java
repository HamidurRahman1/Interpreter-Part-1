
package interpreterPart1;

import java.util.Map;

public abstract class Exp
{
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <exp>");
    }

    public abstract Val Eval(Map<String, Val> valMap);
}