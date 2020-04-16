
package interpreterP1;

public class IntVal extends Val
{
    int val;

    public IntVal(int i)
    {
        val = i;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}