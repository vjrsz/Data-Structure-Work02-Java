package File;

import java.util.List;

public class FileTablePrinter {
    public static void showFiles(List<File> files) {
        int[] columnWidths = calculateColumnWidths(files);
        int totalWidth = calculateTotalWidth(columnWidths);

        printLine(totalWidth);
        printRow(new String[] {"Name", "Type", "Size", "Dt Update", "Dt Created"}, columnWidths);
        printLine(totalWidth);

        for (File file : files) {
            String name = file.getName();
            String type = file.getType();
            String size = String.valueOf(file.getSize());
            String dateUpdate = file.getDateUpdate();
            String dateCreated = file.getDateCreated();

            printRow(new String[] {name, type, size, dateUpdate, dateCreated}, columnWidths);
        }

        printLine(totalWidth);
    }

    private static int[] calculateColumnWidths(List<File> files) {
        int[] columnWidths = new int[5];

        for (File file : files) {
            String name = file.getName();
            String type = file.getType();
            String size = String.valueOf(file.getSize());
            String dateUpdate = file.getDateUpdate();
            String dateCreated = file.getDateCreated();

            columnWidths[0] = Math.max(columnWidths[0], name.length());
            columnWidths[1] = Math.max(columnWidths[1], type.length());
            columnWidths[2] = Math.max(columnWidths[2], size.length());
            columnWidths[3] = Math.max(columnWidths[3], dateUpdate.length());
            columnWidths[4] = Math.max(columnWidths[4], dateCreated.length());
        }

        return columnWidths;
    }

    private static int calculateTotalWidth(int[] columnWidths) {
        int totalWidth = 0;

        for (int width : columnWidths) {
            totalWidth += width; // Add 4 for the padding and separator
        }

        return totalWidth + ((columnWidths.length - 1) * 4);
    }

    private static void printLine(int totalWidth) {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < totalWidth; i++) {
            line.append("-");
        }

        System.out.println(line.toString());
    }

    private static void printRow(String[] data, int[] columnWidths) {
        StringBuilder row = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            String cell = data[i];
            int width = columnWidths[i];
            row.append("| ").append(padRight(cell, width)).append(" ");
        }

        System.out.println(row.toString() + "|");
    }

    private static String padRight(String s, int width) {
        if (s.length() >= width) {
            return s;
        }

        StringBuilder padded = new StringBuilder(s);

        while (padded.length() < width) {
            padded.append(" ");
        }

        return padded.toString();
    }
}

