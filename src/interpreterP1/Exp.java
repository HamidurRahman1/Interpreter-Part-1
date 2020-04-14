import java.util.Map;

public abstract class Exp
{
    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <exp>");
    }

    abstract Val Eval(Map<String, Val> map);
}