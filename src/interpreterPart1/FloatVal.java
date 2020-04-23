
package interpreterPart1;

public class FloatVal extends Val
{
    public float val;

    public FloatVal(float f)
    {
        val = f;
    }

    @Override
    public Val cloneVal() {
        return new FloatVal(val);
    }

    public String toString()
    {
        return String.valueOf(val);
    }
}