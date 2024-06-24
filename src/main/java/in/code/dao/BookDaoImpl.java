//package in.code.dao;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import in.code.db.JdbcUtil;
//import in.code.entity.BookDetails;
//
//public class BookDaoImpl implements IBookDao {
//	private Connection con;
//	private PreparedStatement ps;
//
//	public boolean addBook(BookDetails b) {
//
//		boolean flag = false;
//		try (Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = 
//			con.prepareStatement("insert into book_details(bookName,author,price,bookCategory,status,photoname,email) values(?,?,?,?,?,?,?)"))
//		{
//
//			ps.setString(1, b.getBookName());
//			ps.setString(2, b.getAuthor());
//			ps.setDouble(3, b.getPrice());
//			ps.setString(4, b.getBookCategory());
//			ps.setString(5, b.getStatus());
//			ps.setString(6, b.getPhotoName());
//			ps.setString(7, b.getEmail());
//			int rowUpdated = ps.executeUpdate();
//			if (rowUpdated == 1) {
//				flag = true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return flag;
//	}
//
//	public List<BookDetails> getAllBooks() {
//		List<BookDetails> list = new ArrayList<BookDetails>();
//		BookDetails b = null;
//		try (Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = con.prepareStatement("select * from book_details");
//			ResultSet rs = ps.executeQuery())
//		{
//			while (rs.next()) {
//				b = new BookDetails();
//				b.setBookId(rs.getInt(1));
//				b.setBookName(rs.getString(2));
//				b.setAuthor(rs.getString(3));
//				b.setPrice(rs.getDouble(4));
//				b.setBookCategory(rs.getString(5));
//				b.setStatus(rs.getString(6));
//				b.setPhotoName(rs.getString(7));
//				b.setEmail(rs.getString(8));
//
//				list.add(b);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public BookDetails getBookById(int id) {
//
//		BookDetails b = null;
//		try (Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = con.prepareStatement("select * from book_details where bookId = ?"))
//		{
//			ps.setInt(1, id);
//			try(ResultSet rs = ps.executeQuery()){
//				if (rs.next()) {
//					b = new BookDetails();
//					b.setBookId(rs.getInt(1));
//					b.setBookName(rs.getString(2));
//					b.setAuthor(rs.getString(3));
//					b.setPrice(rs.getDouble(4));
//					b.setBookCategory(rs.getString(5));
//					b.setStatus(rs.getString(6));
//					b.setPhotoName(rs.getString(7));
//					b.setEmail(rs.getString(8));
//					return b;
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public boolean updateEditBooks(BookDetails b) {
//		boolean f = false;
//		try (Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = 
//			con.prepareStatement("update book_details set bookName= ? , author = ? , price = ? , status = ?  where bookId = ?"))
//		{	
//			ps.setString(1, b.getBookName());
//			ps.setString(2, b.getAuthor());
//			ps.setDouble(3, b.getPrice());
//			ps.setString(4, b.getStatus());
//			ps.setInt(5, b.getBookId());
//			int rowUpdated = ps.executeUpdate();
//			if (rowUpdated == 1)
//				f = true;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return f;
//	}
//
//	public boolean deleteBooks(int id) {
//		boolean f = false;
//		try (Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = con.prepareStatement("delete from book_details where bookId = ?"))
//		{	
//			ps.setInt(1, id);
//			int rowUpdated = ps.executeUpdate();
//			if (rowUpdated == 1)
//				f = true;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return f;
//	}
//
//	public List<BookDetails> getNewBooks() {
//		List<BookDetails> list = new ArrayList<BookDetails>();
//		
//		String sql = "select * from book_details where bookCategory = ? and status = ? order by bookId desc ";
//		try(Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = con.prepareStatement(sql)) 
//		{	
//			ps.setString(1, "New Book");
//			ps.setString(2, "Active");
//			
//			try(ResultSet rs = ps.executeQuery()){
//				int i = 1;
//				while (rs.next() && i <= 4) {
//					BookDetails b = new BookDetails();
//					b.setBookId(rs.getInt(1));
//					b.setBookName(rs.getString(2));
//					b.setAuthor(rs.getString(3));
//					b.setPrice(rs.getDouble(4));
//					b.setBookCategory(rs.getString(5));
//					b.setStatus(rs.getString(6));
//					b.setPhotoName(rs.getString(7));
//					b.setEmail(rs.getString(8));
//	
//					list.add(b);
//					i++;
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public List<BookDetails> getOldBooks() {
//		List<BookDetails> list = new ArrayList<BookDetails>();
//		BookDetails b = null;
//		String sql = "select * from book_details where bookCategory = ? and status = ? order by bookId desc ";
//		try(Connection con = JdbcUtil.getJdbcConnection();
//				PreparedStatement ps = con.prepareStatement(sql))
//		{	
//			ps.setString(1, "Old Book");
//			ps.setString(2, "Active");
//			
//			try(ResultSet rs = ps.executeQuery()){
//				int i = 1;
//				while (rs.next() && i <= 4) {
//					b = new BookDetails();
//					b.setBookId(rs.getInt(1));
//					b.setBookName(rs.getString(2));
//					b.setAuthor(rs.getString(3));
//					b.setPrice(rs.getDouble(4));
//					b.setBookCategory(rs.getString(5));
//					b.setStatus(rs.getString(6));
//					b.setPhotoName(rs.getString(7));
//					b.setEmail(rs.getString(8));
//	
//					list.add(b);
//					i++;
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public List<BookDetails> getAllNewBooks() {
//		List<BookDetails> list = new ArrayList<BookDetails>();
//		BookDetails b = null;
//		String sql = "select * from book_details where bookCategory = ? and status = ? order by bookId desc ";
//		try(Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = con.prepareStatement(sql)) {
//			
//			ps.setString(1, "New Book");
//			ps.setString(2, "Active");
//			try(ResultSet rs = ps.executeQuery()){
//				while (rs.next()) {
//					b = new BookDetails();
//					b.setBookId(rs.getInt(1));
//					b.setBookName(rs.getString(2));
//					b.setAuthor(rs.getString(3));
//					b.setPrice(rs.getDouble(4));
//					b.setBookCategory(rs.getString(5));
//					b.setStatus(rs.getString(6));
//					b.setPhotoName(rs.getString(7));
//					b.setEmail(rs.getString(8));
//	
//					list.add(b);
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public List<BookDetails> getAllOldBooks() {
//		List<BookDetails> list = new ArrayList<BookDetails>();
//		BookDetails b = null;
//		String sql = "select * from book_details where bookCategory = ? and status = ? order by bookId desc ";
//		try(Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = con.prepareStatement(sql)) {
//			
//			ps.setString(1, "Old Book");
//			ps.setString(2, "Active");
//
//			try(ResultSet rs = ps.executeQuery()){
//				while (rs.next()) {
//					b = new BookDetails();
//					b.setBookId(rs.getInt(1));
//					b.setBookName(rs.getString(2));
//					b.setAuthor(rs.getString(3));
//					b.setPrice(rs.getDouble(4));
//					b.setBookCategory(rs.getString(5));
//					b.setStatus(rs.getString(6));
//					b.setPhotoName(rs.getString(7));
//					b.setEmail(rs.getString(8));
//	
//					list.add(b);
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public List<BookDetails> getBooksBySearch(String ch) {
//		List<BookDetails> list = new ArrayList<BookDetails>();
//		BookDetails b = null;
//		String sql = "select * from book_details where bookName like ? or author like ? or bookCategory like ? and status = ?";
//		try(Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = con.prepareStatement(sql)) {
//			
//			ps.setString(1, "%" + ch + "%");
//			ps.setString(2, "%" + ch + "%");
//			ps.setString(3, "%" + ch + "%");
//			ps.setString(4, "Active");
//
//			try(ResultSet rs = ps.executeQuery()){
//				while (rs.next()) {
//					b = new BookDetails();
//					b.setBookId(rs.getInt(1));
//					b.setBookName(rs.getString(2));
//					b.setAuthor(rs.getString(3));
//					b.setPrice(rs.getDouble(4));
//					b.setBookCategory(rs.getString(5));
//					b.setStatus(rs.getString(6));
//					b.setPhotoName(rs.getString(7));
//					b.setEmail(rs.getString(8));
//	
//					list.add(b);
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//}

package in.code.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import in.code.entity.BookDetails;
import in.code.util.HibernateUtil;

public class BookDaoImpl implements IBookDao {

    private SessionFactory sessionFactory;

    public BookDaoImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean addBook(BookDetails b) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(b);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (RuntimeException rbEx) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<BookDetails> getAllBooks() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM BookDetails", BookDetails.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BookDetails getBookById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(BookDetails.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateEditBooks(BookDetails b) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(b);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (RuntimeException rbEx) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBooks(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            BookDetails b = session.get(BookDetails.class, id);
            if (b != null) {
                session.delete(b);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (RuntimeException rbEx) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<BookDetails> getNewBooks() {
        try (Session session = sessionFactory.openSession()) {
            Query<BookDetails> query = session.createQuery(
                    "FROM BookDetails WHERE bookCategory = :category AND status = :status ORDER BY bookId DESC",
                    BookDetails.class);
            query.setParameter("category", "New Book");
            query.setParameter("status", "Active");
            query.setMaxResults(4); // Limit to 4 results
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BookDetails> getOldBooks() {
        try (Session session = sessionFactory.openSession()) {
            Query<BookDetails> query = session.createQuery(
                    "FROM BookDetails WHERE bookCategory = :category AND status = :status ORDER BY bookId DESC",
                    BookDetails.class);
            query.setParameter("category", "Old Book");
            query.setParameter("status", "Active");
            query.setMaxResults(4); // Limit to 4 results
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BookDetails> getAllNewBooks() {
        try (Session session = sessionFactory.openSession()) {
            Query<BookDetails> query = session.createQuery(
                    "FROM BookDetails WHERE bookCategory = :category AND status = :status ORDER BY bookId DESC",
                    BookDetails.class);
            query.setParameter("category", "New Book");
            query.setParameter("status", "Active");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BookDetails> getAllOldBooks() {
        try (Session session = sessionFactory.openSession()) {
            Query<BookDetails> query = session.createQuery(
                    "FROM BookDetails WHERE bookCategory = :category AND status = :status ORDER BY bookId DESC",
                    BookDetails.class);
            query.setParameter("category", "Old Book");
            query.setParameter("status", "Active");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BookDetails> getBooksBySearch(String ch) {
        try (Session session = sessionFactory.openSession()) {
            Query<BookDetails> query = session.createQuery(
                    "FROM BookDetails WHERE (bookName LIKE :search OR author LIKE :search OR bookCategory LIKE :search) AND status = :status",
                    BookDetails.class);
            query.setParameter("search", "%" + ch + "%");
            query.setParameter("status", "Active");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}





