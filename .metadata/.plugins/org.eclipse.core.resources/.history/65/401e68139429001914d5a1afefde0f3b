package SpoonImpl_Java;

import SpoonImpl_Java.ReverseOperatorsInIf;

public class Start {
	private static ReverseOperatorsInIf RevOps;

	public static void main(String[] args) {
		try {
			RevOps = new ReverseOperatorsInIf();
			String code = "class SampleClass {public XYDataItem addOrUpdate(Number x, Number y) {\r\n"
					+ "    if (x == null) {\r\n"
					+ "        throw new IllegalArgumentException(\"Null 'x' argument.\");\r\n" + "    }\r\n"
					+ "    XYDataItem overwritten = null;\r\n" + "    int index = indexOf(x);\r\n"
					+ "    if (index >= 0 && !this.allowDuplicateXValues) {\r\n"
					+ "        XYDataItem existing = (XYDataItem) this.data.get(index);\r\n" + "        try {\r\n"
					+ "            overwritten = (XYDataItem) existing.clone();\r\n" + "        }\r\n"
					+ "        catch (CloneNotSupportedException e) {\r\n"
					+ "            throw new SeriesException(\"Couldn't clone XYDataItem!\");\r\n" + "        }\r\n"
					+ "        existing.setY(y);\r\n" + "    }\r\n" + "    else {\r\n"
					+ "        if (this.autoSort) {\r\n"
					+ "            this.data.add(-index - 1, new XYDataItem(x, y));\r\n" + "        }\r\n"
					+ "        else {\r\n" + "            this.data.add(new XYDataItem(x, y));\r\n" + "        }\r\n"
					+ "        if (getItemCount() > this.maximumItemCount) {\r\n"
					+ "            this.data.remove(0);\r\n" + "        }\r\n" + "    }\r\n"
					+ "    fireSeriesChanged();\r\n" + "    return overwritten;\r\n" + "} }";
			System.out.println(RevOps.RevOpsInIf(code));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
