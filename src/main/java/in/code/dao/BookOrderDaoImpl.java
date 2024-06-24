//package in.code.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import in.code.db.JdbcUtil;
//import in.code.entity.Book_Order;
//
//public class BookOrderDaoImpl implements IBookOrderDao {
//
//	private Connection con;
//	private PreparedStatement ps;
//
//	public boolean saveOrder(List<Book_Order> list) {
//		boolean f = false;
//		try(Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = 
//			con.prepareStatement("insert into book_order(orderid,username,email,fulladd,phno,bookname,author,price,paymenttype) values(?,?,?,?,?,?,?,?,?)")) 
//		{
//			
//			con.setAutoCommit(false);
//			
//			for (Book_Order b : list) {
//				ps.setString(1, b.getOrderId());
//				ps.setString(2, b.getUserName());
//				ps.setString(3, b.getEmail());
//				ps.setString(4, b.getFulladd());
//				ps.setString(5, b.getPhno());
//				ps.setString(6, b.getBookName());
//				ps.setString(7, b.getAuthor());
//				ps.setString(8, b.getPrice());
//				ps.setString(9, b.getPaymentType());
//				ps.addBatch();
//
//			}
//			int[] count = ps.executeBatch();
//			con.commit();
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	public List<Book_Order> getBook(String email) {
//		ArrayList<Book_Order> list = new ArrayList<Book_Order>();
//		Book_Order o = null;
//		try(Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = con.prepareStatement("select * from book_order where email = ?"))
//		{
//			ps.setString(1, email);
//			try(ResultSet rs = ps.executeQuery()){
//				while (rs.next()) {
//					o = new Book_Order();
//					o.setId(rs.getInt(1));
//					o.setOrderId(rs.getString(2));
//					o.setUserName(rs.getString(3));
//					o.setEmail(rs.getString(4));
//					o.setFulladd(rs.getString(5));
//					o.setPhno(rs.getString(6));
//					o.setBookName(rs.getString(7));
//					o.setAuthor(rs.getString(8));
//					o.setPrice(rs.getString(9));
//					o.setPaymentType(rs.getString(10));
//					list.add(o);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return list;
//	}
//
//	public List<Book_Order> getAllOrderedBook() {
//		ArrayList<Book_Order> list = new ArrayList<Book_Order>();
//		Book_Order o = null;
//		try(Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = con.prepareStatement("select * from book_order");
//			ResultSet rs = ps.executeQuery())
//		{
//			
//			while (rs.next()) {
//				o = new Book_Order();
//				o.setId(rs.getInt(1));
//				o.setOrderId(rs.getString(2));
//				o.setUserName(rs.getString(3));
//				o.setEmail(rs.getString(4));
//				o.setFulladd(rs.getString(5));
//				o.setPhno(rs.getString(6));
//				o.setBookName(rs.getString(7));
//				o.setAuthor(rs.getString(8));
//				o.setPrice(rs.getString(9));
//				o.setPaymentType(rs.getString(10));
//				list.add(o);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
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

import in.code.entity.Book_Order;
import in.code.util.HibernateUtil;

public class BookOrderDaoImpl implements IBookOrderDao {

    private SessionFactory sessionFactory;

    public BookOrderDaoImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean saveOrder(List<Book_Order> list) {
        boolean flag = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            for (Book_Order b : list) {
                session.saveOrUpdate(b);
            }
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Book_Order> getBook(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<Book_Order> query = session.createQuery("FROM Book_Order WHERE email = :email", Book_Order.class);
            query.setParameter("email", email);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book_Order> getAllOrderedBook() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Book_Order", Book_Order.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}


