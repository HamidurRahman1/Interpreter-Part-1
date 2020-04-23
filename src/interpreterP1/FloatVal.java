
package interpreterP1;

class FloatVal extends Val
{
    public float val;

    public FloatVal(float f)
    {
        val = f;
    }

    public String toString()
    {
        return String.valueOf(val);
    }
}