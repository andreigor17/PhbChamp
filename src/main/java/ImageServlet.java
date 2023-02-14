
import br.com.champ.Servico.AnexoServico;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Image servlet for serving from absolute path.
 *
 * @author andre
 *
 */
@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {

    @EJB
    AnexoServico anexoServico;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getPathInfo().substring(1);

        File file = null;

        file = new File(request.getPathInfo());
        getFileCopy(response, filename, file);
    }

    private void getFileCopy(HttpServletResponse response, String filename, File file) throws IOException {
        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");

        if (file.exists()) {
            Files.copy(file.toPath(), response.getOutputStream());
        }
    }

}
