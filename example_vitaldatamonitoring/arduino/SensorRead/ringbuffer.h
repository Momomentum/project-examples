// ringbuffer.h

#ifndef RINGBUFFER_H
#define RINGBUFFER_H

template <class T> 
class Ringbuffer
{
	public:
		Ringbuffer(unsigned int size)
		{
			m_size = size;
			m_indexIn = 0;
			m_indexOut = 0;
			m_isEmpty = true;

			m_storage = new T[m_size];
		}

		~Ringbuffer() {
			delete[] m_storage;
			m_storage = NULL;
		}

		void push(T elem) volatile {
			m_storage[m_indexIn] = elem;

			if(!m_isEmpty)
			{
				if(m_indexIn == m_indexOut)
				{
					m_indexOut = (m_indexOut + 1) % m_size;
				}
			}

			m_indexIn = (m_indexIn + 1) % m_size;
			
			if(m_isEmpty == true) {
				m_isEmpty = false;
			}
		}

		bool saveNextTokenIn(T& tref) volatile {
			if(!m_isEmpty)
			{
				tref = m_storage[m_indexOut];
				m_indexOut = (m_indexOut +1) % m_size;
				if( m_indexOut == m_indexIn ) {
					m_isEmpty = true;
				}
				return true;
			} else {
				return false;
			}
		}

		bool isEmpty() volatile {
			return isEmpty;
		}
		
	private:
		unsigned int m_size;
		unsigned int m_indexIn;
		unsigned int m_indexOut;
		bool m_isEmpty;
		T* m_storage;
};

#endif
