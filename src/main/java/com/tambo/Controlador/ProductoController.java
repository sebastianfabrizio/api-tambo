package com.tambo.Controlador;
import com.tambo.Model.Producto;
import com.tambo.Service.ProductoService;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.attoparser.dom.Document;
import org.attoparser.dom.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/productos")
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    @PostMapping("/productos")
    public Producto createProducto(@RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/productos/categoria/{categoria}")
    public List<Producto> getProductosByCategoria(@PathVariable String categoria) {
        return productoService.getProductosByCategoria(categoria);
    }
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto updatedProducto = productoService.updateProducto(id, producto);
        return ResponseEntity.ok(updatedProducto);
    }




    @GetMapping("/productos/pdf")
    public ResponseEntity<byte[]> generateReport() {
        List<Producto> productos = productoService.getAllProductos();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);

            // Agregar título
            contentStream.beginText();
            contentStream.newLineAtOffset(220, 800);
            contentStream.showText("Productos Tambo");
            contentStream.endText();

            // Configuración de la tabla
            float margin = 60;
            float yStart = 740;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float yPosition = yStart;

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            float rowHeight = 16f;
            float cellMargin = -5f;
            float textMargin = 7f; // Desplazamiento hacia la derecha

            // Dibujar cabeceras
            String[] headers = {"Id", "Producto", "Marca", "Categoria", "Precio", "Descripcion", "Stock"};
            float[] colWidths = {30, 80, 60, 60, 50, 100, 50};

            yPosition -= rowHeight;
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            for (int i = 0; i < headers.length; i++) {
                contentStream.beginText();
                contentStream.newLineAtOffset(margin + cellMargin + getColumnOffset(colWidths, i) + textMargin, yPosition - cellMargin / 2);
                contentStream.showText(headers[i]);
                contentStream.endText();
                // Dibujar borde de la celda
                contentStream.addRect(margin + getColumnOffset(colWidths, i), yPosition, colWidths[i], rowHeight);
                contentStream.stroke();
            }

            // Dibujar filas
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            for (Producto producto : productos) {
                yPosition -= rowHeight;
                if (yPosition <= margin) {
                    contentStream.close();
                    PDPage newPage = new PDPage(PDRectangle.A4);
                    document.addPage(newPage);
                    contentStream = new PDPageContentStream(document, newPage);
                    yPosition = yStart - rowHeight;
                }

                String[] data = {
                        String.valueOf(producto.getId()),
                        producto.getProducto(),
                        producto.getMarca(),
                        producto.getCategoria(),
                        String.valueOf(producto.getPrecio()),
                        producto.getDescripcion(),
                        String.valueOf(producto.getStock())
                };

                for (int i = 0; i < data.length; i++) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin + cellMargin + getColumnOffset(colWidths, i) + textMargin, yPosition - cellMargin / 2);
                    contentStream.showText(data[i]);
                    contentStream.endText();
                    // Dibujar borde de la celda
                    contentStream.addRect(margin + getColumnOffset(colWidths, i), yPosition, colWidths[i], rowHeight);
                    contentStream.stroke();
                }
            }

            contentStream.close();
            document.save(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=productos.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(out.toByteArray());
    }

    private float getColumnOffset(float[] colWidths, int colIndex) {
        float offset = 0;
        for (int i = 0; i < colIndex; i++) {
            offset += colWidths[i];
        }
        return offset;
    }



}