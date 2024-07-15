package com.company.inventary.util;
import com.company.inventary.model.Product;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class ProductExcelExport {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Product> product;

    public ProductExcelExport( List<Product> product) {
        this.product = product;
        this.workbook = new XSSFWorkbook();
    }

    private void WriteHeaderLine(){
        sheet=workbook.createSheet("Resultado");
        Row row= sheet.createRow(0);
        CellStyle style=workbook.createCellStyle(); //creo los estilos de la celda fila
        // aqui el tipo de letra bold el color y luego lo agrego al stilo
        XSSFFont font=workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        font.setColor(IndexedColors.BLUE.getIndex());
        // agrego los estilos
        style.setFont(font);

        createCell(row,0,"ID" ,style);
        createCell(row,1,"Nombre" ,style);
        createCell(row,2,"Precio" ,style);
        createCell(row,3,"Cantidad" ,style);
        createCell(row,4,"Categoria" ,style);
    }

    private void createCell(Row row ,int contadorColumna, Object value, CellStyle style){

        sheet.autoSizeColumn(contadorColumna);
        Cell cell=row.createCell(contadorColumna);
        if (value instanceof Integer){
            cell.setCellValue((Integer)value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

    private void writedataLine(){
        int rowContadorFilas=1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Product resultado:product) {
            Row row=sheet.createRow(rowContadorFilas++);
            int columnCount=0;
            createCell(row,columnCount++,String.valueOf(resultado.getId()),style);
            createCell(row,columnCount++,resultado.getName(),style);
            createCell(row,columnCount++,resultado.getPrecio(),style);
            createCell(row,columnCount++,resultado.getAccount(),style);
            createCell(row,columnCount++,resultado.getCategory().getName(),style);
        }
    }

    public void export(HttpServletResponse response)throws IOException {
        WriteHeaderLine();//escribe la cabeceraa
        writedataLine();// escribe los datos en el campo las celdas
        ServletOutputStream servletOutput=response.getOutputStream();
        workbook.write(servletOutput);
        workbook.close();
        servletOutput.close();

    }
}
