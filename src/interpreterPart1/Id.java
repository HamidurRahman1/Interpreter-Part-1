
package interpreterPart1;

import java.util.Map;

public class Id extends Exp
{
    public String id;

    public Id(String s)
    {
        id = s;
    }

    public void printParseTree(String indent)
    {
        super.printParseTree(indent);
        String indent1 = indent+" ";
        IO.displayln(indent1 + indent1.length() + " " + id);
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        Val idVal = valMap.get(id);
        if (idVal != null) return idVal.cloneVal();
        else
        {
            System.out.println( "variable "+id+" does not have a value" );
            return null;
        }
    }
}