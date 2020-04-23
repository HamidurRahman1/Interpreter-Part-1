
package interpreterP1;

public class BoolVal extends Val
{
    public boolean val;

    public BoolVal(boolean boolVal)
    {
        val = boolVal;
    }

    @Override
    public String toString()
    {
        return String.valueOf(val);
    }
}