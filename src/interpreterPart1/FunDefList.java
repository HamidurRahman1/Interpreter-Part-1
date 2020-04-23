
package interpreterPart1;

public abstract class FunDefList
{
    public void printParseTree(String indent)
    {
        FunDefList p = this;

        while ( p instanceof MultipleFunDef )
        {
            ((MultipleFunDef)p).funDef.printParseTree(indent);
            p = ((MultipleFunDef)p).funDefList;
        }

        p.printParseTree(indent);
    }
}