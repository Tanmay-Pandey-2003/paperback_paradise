//package in.code.dao;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import in.code.db.JdbcUtil;
//import in.code.entity.User;
//
//public class UserDaoImpl implements IUserDao {
//	private Connection con;
//
//	public boolean userRegister(User us) {
//
//		boolean flag = false;
//		try (Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = 
//			con.prepareStatement("insert into book_users(name,email,password,phno) values(?,?,?,?)"))
//		{
//			ps.setString(1, us.getName());
//			ps.setString(2, us.getEmail());
//			ps.setString(3, us.getPhno());
//			ps.setString(4, us.getPassword());
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
//	public User login(String email, String password) {
//		User us = null;
//		try (Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = 
//			con.prepareStatement("select * from book_users where email = ? and password = ?"))
//		{
//			ps.setString(1, email);
//			ps.setString(2, password);
//			try(ResultSet rs = ps.executeQuery()){
//				if (rs.next()) {
//					us = new User();
//					us.setId(rs.getInt(1));
//					us.setName(rs.getString(2));
//					us.setEmail(rs.getString(3));
//					us.setPhno(rs.getString(4));
//					us.setPassword(rs.getString(5));
//					us.setAddress(rs.getString(6));
//					us.setLandmark(rs.getString(7));
//					us.setCity(rs.getString(8));
//					us.setState(rs.getString(9));
//					us.setPincode(rs.getString(10));
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return us;
//	}
//
//	public boolean checkpassword(int id, String password) {
//		boolean f = false;
//		try(Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = 
//			con.prepareStatement("select * from book_users where id = ? and password = ?")) 
//		{
//			ps.setInt(1, id);
//			ps.setString(2, password);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				f = true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return f;
//	}
//
//	public boolean updateProfile(User us) {
//		boolean f = false;
//		try(Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = 
//			con.prepareStatement("update book_users set name = ? ,email = ? ,phno= ? where id = ? "))
//		{
//			ps.setString(1, us.getName());
//			ps.setString(2, us.getEmail());
//			ps.setString(3, us.getPhno());
//			ps.setInt(4, us.getId());
//			int rowAffected = ps.executeUpdate();
//			if (rowAffected >= 1) {
//				f = true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return f;
//	}
//
//	public boolean checkUser(String email) {
//		boolean f = false;
//		try(Connection con = JdbcUtil.getJdbcConnection();
//			PreparedStatement ps = 
//			con.prepareStatement("select * from book_users where email = ?")) 
//		{
//			ps.setString(1, email);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				f = true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return f;
//	}
//
//}

package in.code.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.code.entity.User;
import in.code.util.HibernateUtil;

public class UserDaoImpl implements IUserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory(); // Initialize session factory
    }

    @Override
    public boolean userRegister(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user); // Save the user object
            transaction.commit(); // Commit the transaction
            return true; // Return true if successful
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback transaction on exception
            }
            e.printStackTrace();
            return false; // Return false if an exception occurs
        }
    }

    @Override
    public User login(String email, String password) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            User user = query.uniqueResult();
            if (user != null && user.getPassword().equals(password)) {
                return user; // Return user if found and password matches
            } else {
                return null; // Return null if user not found or password doesn't match
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null on exception
        }
    }

    @Override
    public boolean checkpassword(int id, String password) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id); // Get user by id
            if (user != null && user.getPassword().equals(password)) {
                return true; // Return true if password matches
            } else {
                return false; // Return false if user not found or password doesn't match
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false on exception
        }
    }

    @Override
    public boolean updateProfile(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Retrieve the existing user entity
            User existingUser = session.get(User.class, user.getId());
            if (existingUser == null) {
                // Handle case where user does not exist
                return false; // Return false if user not found
            }

            // Update user fields
            if (user.getName() != null) {
                existingUser.setName(user.getName());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getPhno() != null) {
                existingUser.setPhno(user.getPhno());
            }
            // Merge updated user entity
            session.merge(existingUser);

            transaction.commit();
            return true; // Return true if update successful
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback transaction on exception
            }
            e.printStackTrace();
            return false; // Return false if an exception occurs
        }
    }

    @Override
    public boolean checkUser(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResult() != null; // Return true if user exists
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false on exception
        }
    }
}


