
package interpreterPart1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DivE extends FunExp
{
    public DivE(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return "/";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        if(expList.getClass() == EmptyExpList.class) return new IntVal(1);
        else
        {
            NonEmptyExpList ne = (NonEmptyExpList) expList;
            valMap.put(getFunOp(), new IntVal(1));
            List<Val> l = new LinkedList<>();

            while(ne.expList != null)
            {
                l.add(ne.exp.Eval(valMap));
                if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
                else break;
            }

            Collections.reverse(l);

            for(int i = 0; i < l.size(); i++)
            {
                Class cls = l.get(i).getClass();
                Val v = valMap.get(getFunOp());
                if(cls == FloatVal.class || v.getClass() == FloatVal.class)
                {
                    if(v.getClass() == FloatVal.class)
                    {
                        if(l.get(i).getClass() == IntVal.class)
                        {
                            int f1 = (((IntVal) l.get(i))).val;
                            FloatVal f2 = (FloatVal)v;
                            valMap.put(getFunOp(), new FloatVal(f1/f2.val));
                        }
                        else
                        {
                            float f1 = (((FloatVal) l.get(i))).val;
                            FloatVal f2 = (FloatVal)v;
                            valMap.put(getFunOp(), new FloatVal(f1/f2.val));
                        }
                    }
                    else
                    {
                        if(l.get(i).getClass() == IntVal.class)
                        {
                            int f1 = (((IntVal) l.get(i))).val;
                            IntVal f2 = (IntVal)v;
                            valMap.put(getFunOp(), new IntVal(f1/f2.val));
                        }
                        else
                        {
                            float f1 = (((FloatVal) l.get(i))).val;
                            IntVal f2 = (IntVal)v;
                            valMap.put(getFunOp(), new FloatVal(f1/f2.val));
                        }
                    }
                }
                else
                {
                    try
                    {
                        if(l.get(i).getClass() == IntVal.class)
                        {
                            int f1 = (((IntVal) l.get(i))).val;
                            IntVal f2 = (IntVal)v;
                            valMap.put(getFunOp(), new IntVal(f1/f2.val));
                        }
                        else
                        {
                            int f1 = (int)(((FloatVal) l.get(i))).val;
                            IntVal f2 = (IntVal)v;
                            valMap.put(getFunOp(), new IntVal(f1/f2.val));
                        }
                    }
                    catch (Exception ex)
                    {
                        System.out.println("Error: integer division by 0");
                        return null;
                    }
                }
            }

            return valMap.get(getFunOp());
        }
    }
}