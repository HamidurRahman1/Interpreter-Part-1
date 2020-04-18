
package interpreterP1;

public class FloatVal extends Val
{
    public float val;

    public FloatVal(float f)
    {
        val = f;
    }

    public Val cloneVal()
    {
        return new FloatVal(val);
    }

    public float floatVal()
    {
        return val;
    }

    public boolean isZero()
    {
        return val == 0.0f;
    }

    public String toString()
    {
        return String.valueOf(val);
    }
}