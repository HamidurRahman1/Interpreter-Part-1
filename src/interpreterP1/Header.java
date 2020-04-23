
package interpreterP1;

public class Header
{
    public String funName;
    public ParameterList parameterList;

    public Header(String f, ParameterList p)
    {
        funName = f;
        parameterList = p;
    }

    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <header>");
        String indent1 = indent+" ";
        IO.displayln(indent1 + indent1.length() + " <fun name> " + funName);
        IO.displayln(indent1 + indent1.length() + " <parameter list>");
        String indent2 = indent1+" ";

        ParameterList p = parameterList;
        while ( p instanceof NonEmptyParameterList )
        {
            IO.displayln(indent2 + indent2.length() + " " + ((NonEmptyParameterList)p).id);
            p = ((NonEmptyParameterList)p).parameterList;
        }
    }
}