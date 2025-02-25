package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dto.BookDTO;
import java.awt.print.Book;
import java.util.List;
import dto.UserDTO;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/header.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-16WWW\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("\n");
      out.write("        <style>\n");
      out.write("            .book-table {\n");
      out.write("                width: 100%;\n");
      out.write("                border-collapse: collapse;\n");
      out.write("                margin: 25px 0;\n");
      out.write("                font-size: 14px;\n");
      out.write("                font-family: Arial, sans-serif;\n");
      out.write("                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .book-table thead th {\n");
      out.write("                background-color: #009879;\n");
      out.write("                color: #ffffff;\n");
      out.write("                text-align: left;\n");
      out.write("                font-weight: bold;\n");
      out.write("                padding: 12px 15px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .book-table tbody tr {\n");
      out.write("                border-bottom: 1px solid #dddddd;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .book-table tbody tr:nth-of-type(even) {\n");
      out.write("                background-color: #f3f3f3;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .book-table tbody tr:last-of-type {\n");
      out.write("                border-bottom: 2px solid #009879;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .book-table tbody td {\n");
      out.write("                padding: 12px 15px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .book-table tbody tr:hover {\n");
      out.write("                background-color: #f5f5f5;\n");
      out.write("                transition: 0.3s ease;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            /* Responsive design */\n");
      out.write("            @media screen and (max-width: 600px) {\n");
      out.write("                .book-table {\n");
      out.write("                    font-size: 12px;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                .book-table thead th,\n");
      out.write("                .book-table tbody td {\n");
      out.write("                    padding: 8px 10px;\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </style> \n");
      out.write("    </head>\n");
      out.write("    <body> \n");
      out.write("        ");
      out.write("\n");
      out.write("<style>\n");
      out.write("    * {\n");
      out.write("        margin: 0;\n");
      out.write("        padding: 0;\n");
      out.write("        box-sizing: border-box;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .header {\n");
      out.write("        background-color: #2c3e50;\n");
      out.write("        padding: 1rem 0;\n");
      out.write("        width: 100%;\n");
      out.write("        top: 0;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .container {\n");
      out.write("        max-width: 1200px;\n");
      out.write("        margin: 0 auto;\n");
      out.write("        padding: 0 1rem;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .nav {\n");
      out.write("        display: flex;\n");
      out.write("        justify-content: space-between;\n");
      out.write("        align-items: center;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .logo {\n");
      out.write("        color: #fff;\n");
      out.write("        font-size: 1.5rem;\n");
      out.write("        font-weight: bold;\n");
      out.write("        text-decoration: none;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .menu {\n");
      out.write("        display: flex;\n");
      out.write("        list-style: none;\n");
      out.write("        gap: 2rem;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .menu-item a {\n");
      out.write("        color: #fff;\n");
      out.write("        text-decoration: none;\n");
      out.write("        font-size: 1rem;\n");
      out.write("        transition: color 0.3s ease;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .menu-item a:hover {\n");
      out.write("        color: #3498db;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .search-bar {\n");
      out.write("        display: flex;\n");
      out.write("        align-items: center;\n");
      out.write("        background: #fff;\n");
      out.write("        border-radius: 20px;\n");
      out.write("        padding: 0.5rem 1rem;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .search-input {\n");
      out.write("        border: none;\n");
      out.write("        outline: none;\n");
      out.write("        padding: 0.2rem;\n");
      out.write("        width: 200px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .search-button {\n");
      out.write("        background: none;\n");
      out.write("        border: none;\n");
      out.write("        cursor: pointer;\n");
      out.write("        color: #2c3e50;\n");
      out.write("    }\n");
      out.write("</style>\n");
      out.write("\n");
      out.write("<header class=\"header\">\n");
      out.write("   \n");
      out.write("    <div class=\"container\">\n");
      out.write("        <nav class=\"nav\">\n");
      out.write("            <a href=\"#\" class=\"logo\">SHOP BOOKS</a>\n");
      out.write("            <ul class=\"menu\">\n");
      out.write("                <li class=\"menu-item\"><a href=\"#\">Trang chủ</a></li>\n");
      out.write("                <li class=\"menu-item\"><a href=\"#\">Sản phẩm</a></li>\n");
      out.write("                <li class=\"menu-item\"><a href=\"#\">Giỏ hàng</a></li>\n");
      out.write("                <li class=\"menu-item\"><a href=\"#\">Liên hệ</a></li>\n");
      out.write("            </ul>\n");
      out.write("            <div class=\"search-bar\">\n");
      out.write("                <input type=\"text\" class=\"search-input\" placeholder=\"Tìm kiếm...\">\n");
      out.write("                <button class=\"search-button\">🔍</button>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("    </div>\n");
      out.write("</header>");
      out.write("\n");
      out.write("        <div style=\"min-height: 500px; padding: 10px\">\n");
      out.write("            ");
                if (session.getAttribute("user") != null) {
                    UserDTO user = (UserDTO) session.getAttribute("user");
            
      out.write("\n");
      out.write("            <h1> Welcome ");
      out.print(user.getFullName());
      out.write(" </h1>\n");
      out.write("            <form action=\"MainController\">\n");
      out.write("                <input type=\"hidden\" name=\"action\" value=\"logout\"/>\n");
      out.write("                <input type=\"submit\" value=\"Logout\"/>\n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("            <br/>\n");
      out.write("            ");

                String searchTerm = request.getAttribute("searchTerm")+"";
                searchTerm = searchTerm.equals("null")?"":searchTerm;
            
      out.write("\n");
      out.write("            <form action=\"MainController\">\n");
      out.write("                <input type=\"hidden\" name=\"action\" value=\"search\"/>\n");
      out.write("                Search Books: <input type=\"text\" name=\"searchTerm\" value=\"");
      out.print(searchTerm);
      out.write("\"/>\n");
      out.write("                <input type=\"submit\" value=\"Search\"/>\n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("            ");

                if (request.getAttribute("books") != null) {
                    List<BookDTO> books = (List<BookDTO>) request.getAttribute("books");

            
      out.write("\n");
      out.write("            <table class=\"book-table\">\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>BookID</th>\n");
      out.write("                        <th>Title</th>\n");
      out.write("                        <th>Author</th>\n");
      out.write("                        <th>PublishYear</th>\n");
      out.write("                        <th>Price</th>\n");
      out.write("                        <th>Quantity</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");
            for (BookDTO b : books) {
                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print(b.getBookID());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(b.getTitle());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(b.getAuthor());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(b.getPublishYear());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(b.getPrice());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(b.getQuantity());
      out.write("</td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");

                        }
                    
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("            ");
    }
            } else { 
      out.write("\n");
      out.write("            You do not have permission to access this content.\n");
      out.write("            ");
}
      out.write("\n");
      out.write("        </div>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
