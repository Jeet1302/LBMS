/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
package Frames;

 class Book {
    
    private String bookid;
    private String title ;
    private String author;
    private String status;
   

    public Book(String bookid, String title, String author, String status) { 
        this.bookid = bookid;
        this.title = title;
        this.author = author;
        this.status = status;
    } 
    
    public String getBookid() {
        
        return bookid;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }
}
