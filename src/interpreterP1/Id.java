
package interpreterP1;

import java.util.Map;

class Id extends Exp
{
    String id;

    Id(String s)
    {
        id = s;
    }

    void printParseTree(String indent)
    {
        super.printParseTree(indent);
        String indent1 = indent+" ";
        IO.displayln(indent1 + indent1.length() + " " + id);
    }

    @Override
    Val Eval(Map<String, Val> map)
    {
        Val idVal = map.get(id);
        if (idVal != null) return idVal.cloneVal();
        else
        {
            System.out.println( "variable "+id+" does not have a value" );
            return null;
        }
    }
}